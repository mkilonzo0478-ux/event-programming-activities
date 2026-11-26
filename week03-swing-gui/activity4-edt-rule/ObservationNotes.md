# Activity 4: EDT Observation & Self-Check Notes

**Author:** Michael Kilonzo

## Observed Behavior: `FreezeDemo` vs. `FixedDemo`

### 1. `FreezeDemo.java` (BAD Practice — Blocking the EDT)
- **What happens:** Clicking `"Freeze the UI"` calls `Thread.sleep(5000)` directly inside the `ActionListener`, which executes on `AWT-EventQueue-0` (the Event Dispatch Thread).
- **Observed symptoms:**
  - The `"Working..."` label text does not even repaint immediately because the paint event waits behind the sleeping listener.
  - For 5 full seconds, the window cannot be moved, resized, repainted, or closed, and clicking the button queues up clicks without visual feedback.
  - Only after `Thread.sleep(5000)` finishes does the EDT resume processing events and display `"Done!"`.

### 2. `FixedDemo.java` (GOOD Practice — Background Thread + `SwingUtilities.invokeLater`)
- **What happens:** Clicking `"Do Long Operation"` updates the label to `"Working..."`, disables the button on the EDT, and spawns a `new Thread(...)` (`Thread-0`, `Thread-1`, etc.) to run `Thread.sleep(5000)`.
- **Observed symptoms:**
  - The UI remains completely responsive throughout the 5-second delay — the window moves, resizes, and repaints smoothly.
  - Once the background thread completes, `SwingUtilities.invokeLater(...)` schedules the label update (`"Done!"`) and re-enables the button safely on `AWT-EventQueue-0`.

## Thread Verification Reference

| Thread Name | Meaning | Safe for Swing GUI calls? |
| :--- | :--- | :--- |
| `AWT-EventQueue-0` | Event Dispatch Thread (EDT) | Yes |
| `main` | Initial JVM main thread | No — wrap with `SwingUtilities.invokeLater` |
| `Thread-0`, `Thread-1` | Worker / background thread | No — must dispatch GUI updates via `SwingUtilities.invokeLater` |

## Self-Check Questions & Answers

1. **Why does `Thread.sleep()` on the EDT freeze the UI?**
   - The EDT runs Swing's single event loop, handling all input events, window movement/resizing, and repaint requests sequentially. Sleeping on the EDT halts the event loop, so no UI events can be processed until the sleep finishes.

2. **What is the safe way to update a label from a background thread?**
   - Schedule the update onto the EDT using `SwingUtilities.invokeLater(() -> label.setText("..."))`.

3. **What happens if you violate the EDT rule?**
   - Blocking the EDT freezes the application. Mutating Swing components from non-EDT threads causes race conditions, visual glitches, stale UI state, or runtime exceptions because Swing is not thread-safe.
