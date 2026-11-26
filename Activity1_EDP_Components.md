# Activity 1: "Spot the Components" — Real-World EDP Scavenger Hunt

**Author:** Michael Kilonzo

**Learning Outcomes Addressed:** #1 (Define EDP components), #4 (Identify real-world event-driven systems)

---

## Completed EDP Components Table (8+ Systems)

| System | Event Source | Event | Listener | Handler / Action |
| :--- | :--- | :--- | :--- | :--- |
| **1. ATM Machine Operation** | Keypad button | Button pressed | ATM controller software | Capture digit / verify PIN |
| **1. ATM Machine Operation** | Card slot | Card inserted | Magnetic/chip card reader | Read card account data and prompt for PIN |
| **1. ATM Machine Operation** | Cash dispenser | Cash taken | Optical/infrared dispenser sensor | Close shutter, print receipt, and end transaction |
| **2. Microwave Oven Use** | Number pad / "Start +30s" button | Button pressed | Microwave microcontroller | Add cooking time and energize magnetron |
| **2. Microwave Oven Use** | Door latch | Door opened mid-cycle | Door safety interlock switch | Immediately cut power and pause timer |
| **2. Microwave Oven Use** | Countdown timer | Timer reaches `00:00` | Timer interrupt handler | Turn off heating element and sound chime |
| **3. Traffic Light System** | Roadway inductive loop sensor | Vehicle arrives at intersection | Traffic signal controller | Queue phase change for cross-traffic green light |
| **3. Traffic Light System** | Pedestrian crosswalk button | Button pressed | Crosswalk input monitor | Schedule "Walk" signal on next safe cycle |
| **3. Traffic Light System** | Phase timer | Interval elapsed | Signal state machine | Transition lights (Green -> Yellow -> Red) |
| **4. Video Game Gameplay** | Keyboard / gamepad | Key or jump button pressed | Game engine input listener | Apply upward velocity to player character |
| **4. Video Game Gameplay** | Player hitbox / enemy collider | Collision detected | Physics collision listener | Deduct health points, play sound, trigger invulnerability frames |
| **5. Elevator Operation** | Hall call button (Up/Down) | Call button pressed | Elevator dispatch controller | Add floor to request queue and dispatch nearest cab |
| **5. Elevator Operation** | Floor arrival sensor | Cab reaches target floor | Elevator motion controller | Decelerate motor, level cab, and open doors |
| **5. Elevator Operation** | Door infrared curtain | Obstruction detected | Door safety listener | Halt door closing and reopen doors |
| **6. Smartphone Touchscreen** | Capacitive touch digitizer | Finger tap or swipe gesture | OS UI event dispatcher | Open tapped app or scroll feed smoothly |
| **6. Smartphone Touchscreen** | Battery monitor IC | Charge drops below 15% | Power management service | Display low-battery alert and enable battery saver |
| **7. Online Shopping Website** | "Add to Cart" button | Mouse click (`onclick`) | Browser DOM event listener | Send API request, increment cart badge, update subtotal |
| **7. Online Shopping Website** | Search input box | Text changed (`oninput`) | Search autocomplete handler | Fetch and display matching product suggestions |
| **8. Supermarket Self-Checkout** | Barcode scanner | Barcode scanned | POS checkout software | Look up item price, add to receipt, and announce item |
| **8. Supermarket Self-Checkout** | Bagging area scale | Weight mismatch detected | Scale validation listener | Pause scanning and display "Unexpected item in bagging area" |
| **9. Smart Car Dashboard** | Tire pressure sensor (TPMS) | Pressure drops below threshold | Vehicle ECU monitor | Illuminate warning icon and log diagnostic code |
| **9. Smart Car Dashboard** | Brake pedal switch | Brake pedal pressed | Cruise control listener | Disengage cruise control and illuminate brake lights |

---

## Step 4: Self-Check Questions

1. **What is the difference between an event source and an event listener?**
   - An **event source** is the component or object that detects an interaction or state change and *generates* (fires) an event (for example, a `JButton`, a microwave door latch, or a keypad).
   - An **event listener** is the object or interface *registered* to watch for that specific event from a source and trigger the corresponding **event handler** when the event occurs (for example, an `ActionListener` attached to a button).

2. **Can one event source have multiple listeners? Give an example.**
   - **Yes.** A single event source can notify multiple registered listeners when an event occurs.
   - *Example:* In a Java Swing UI or web app, clicking a single "Submit Order" button (one event source) can trigger one listener that validates and submits the form, a second listener that disables the button to prevent double-clicks, and a third listener that logs analytics. In the real world, pressing an elevator's "Emergency" button can simultaneously notify the building security desk listener and an intercom/siren listener.

3. **Can one listener handle events from multiple sources? Give an example.**
   - **Yes.** One listener instance can be registered with several different event sources and inspect the event object (e.g., `e.getSource()` or `e.getActionCommand()`) to decide how to respond.
   - *Example:* In a calculator GUI, a single shared `ActionListener` can be attached to all 10 digit buttons (`0`–`9`) to append the clicked button's label text to the display. Similarly, in a security alarm system, one central alarm listener monitors motion sensors from multiple rooms.
