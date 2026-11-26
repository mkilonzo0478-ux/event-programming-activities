# Event-Driven System Design

**Author:** Michael Kilonzo

## System Name: Smart Home Controller

---

## Part 1: Event Sources
Components in the Smart Home Controller that generate events:
1. **Lights Toggle (`JToggleButton`)** — Generates an `ActionEvent` when the user switches home lighting ON or OFF.
2. **Security System Toggle (`JToggleButton`)** — Generates an `ActionEvent` when the user arms or disarms the home security system.
3. **Front Door Sensor (`JButton` simulation)** — Generates an `ActionEvent` when the front door opens.
4. **Hallway Motion Sensor (`JButton` simulation)** — Generates an `ActionEvent` when movement is detected indoors.
5. **Thermostat Slider (`JSlider`)** — Generates a `ChangeEvent` whenever the target temperature is adjusted between 16°C and 30°C.
6. **Alarm Reset Button (`JButton`)** — Generates an `ActionEvent` when the homeowner silences/resets an active intrusion alarm.

---

## Part 2: Events

| Event Name | Source | Description |
| :--- | :--- | :--- |
| `LightsToggled` (`ActionEvent`) | Lights Toggle | Fired when the user clicks the Lights button to toggle state |
| `SecurityToggled` (`ActionEvent`) | Security Toggle | Fired when the user arms or disarms the security system |
| `DoorOpened` (`ActionEvent`) | Front Door Sensor | Fired when the front door contact sensor detects the door opening |
| `MotionDetected` (`ActionEvent`) | Motion Sensor | Fired when the indoor PIR sensor detects movement |
| `TemperatureChanged` (`ChangeEvent`) | Thermostat Slider | Fired continuously while dragging and when released on a new target temperature |
| `AlarmReset` (`ActionEvent`) | Alarm Reset Button | Fired when the user acknowledges and clears an active alarm |

---

## Part 3: Event Handlers

| Event | Handler Action |
| :--- | :--- |
| `LightsToggled` | Update light state (`ON`/`OFF`), update button text and status summary, and append a timestamped entry to the event log |
| `SecurityToggled` | Toggle armed state; if disarmed while an alarm is active, clear the alarm alert and restore normal UI styling; log state change |
| `DoorOpened` | Log door sensor trigger; if security is armed, activate intrusion alarm (red alert banner + status update); otherwise log normal entry |
| `MotionDetected` | Log motion trigger; if security is armed, activate intrusion alarm; if disarmed and lights are off, automatically turn lights ON for convenience |
| `TemperatureChanged` | Update live thermostat display label immediately; once slider adjustment settles (`!getValueIsAdjusting()`), log the new target temperature |
| `AlarmReset` | Clear active alarm state, restore default background color, update status summary, and log that the alarm was acknowledged |

---

## Part 4: Event Flow Diagram

```text
+---------------+      +------------------+      +-----------------+
|  User Action  | ---> |   Event Source   | ---> |   Event Object  |
| (Click/Slide) |      | (Button/Slider)  |      | (Action/Change) |
+---------------+      +------------------+      +-----------------+
                                                          |
                                                          v
+---------------+      +------------------+      +-----------------+
|  GUI Update   | <--- |  Event Handler   | <--- |   Event Queue   |
| (State & Log) |      | (Listener Logic) |      |   (AWT FIFO)    |
+---------------+      +------------------+      +-----------------+
                              ^                          |
                              |      Event Loop (EDT)    |
                              +--------------------------+
```

---

## Part 5: Potential Issues

1. **Blocking the EDT during hardware/network calls** — *Prevention:* In a real smart home hub, communicating with smart bulbs, locks, or cloud APIs over Wi-Fi/Zigbee can take seconds. Run I/O on a background thread (`SwingWorker` or `Thread` pool) and marshal UI updates back via `SwingUtilities.invokeLater()`.
2. **Event flooding from continuous sensors (e.g., slider drag or rapid motion triggers)** — *Prevention:* Check `slider.getValueIsAdjusting()` before writing permanent logs or sending device commands, and debounce repeated sensor events within a cooldown window.
3. **Stale UI state or unacknowledged alarm lockouts** — *Prevention:* Derive the status summary from a single source of truth (`updateStatusSummary()`), and ensure both the "Reset Alarm" button and disarming the security toggle cleanly restore normal UI state.
