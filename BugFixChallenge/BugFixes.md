# Bug Fix Documentation

**Author:** Michael Kilonzo

## Bug 1: Blocking the EDT
- **Symptom:** UI freezes when the "Process" button is clicked; the window cannot be moved, resized, or clicked for 5 seconds.
- **Cause:** Long-running operation (`Thread.sleep(5000)`) runs directly inside the `ActionListener` on the Event Dispatch Thread (EDT).
- **Fix:** Run the long operation in a background thread and marshal the UI updates back onto the EDT using `SwingUtilities.invokeLater()`.
- **Code Change:** Wrapped `Thread.sleep(5000)` inside `new Thread(() -> { ... }).start()`, set `"Processing..."` and disabled `processButton` while running, and moved `statusLabel.setText("Done!")` (plus re-enabling `processButton`) inside `SwingUtilities.invokeLater(() -> { ... })`.

## Bug 2: Missing Listener
- **Symptom:** Clicking the "Increment" button does nothing; the count stays at `Count: 0`.
- **Cause:** No `ActionListener` was registered on `incrementButton`, so click events were never handled.
- **Fix:** Added an `ActionListener` to `incrementButton` to update application state and refresh the label.
- **Code Change:** Added `incrementButton.addActionListener(e -> { count++; countLabel.setText("Count: " + count); });`.

## Bug 3: Wrong Thread
- **Symptom:** Erratic UI behavior, stale rendering, or potential thread-safety crashes when the clock runs.
- **Cause:** `timeLabel.setText(...)` was called directly from a background worker thread instead of the EDT (violating Swing's single-thread rule).
- **Fix:** Used `SwingUtilities.invokeLater()` for all Swing component updates from the background thread, marked `running` as `volatile`, and prevented duplicate threads on repeated clicks.
- **Code Change:** Replaced the direct `timeLabel.setText(...)` call inside the `while (running)` loop with `SwingUtilities.invokeLater(() -> timeLabel.setText(currentTime));`, declared `running` as `volatile`, and guarded against starting multiple clock threads.

## Bug 4: Memory Leak
- **Symptom:** Memory usage grows over time even after clicking "Remove All" to remove dynamic buttons from the screen.
- **Cause:** `buttonPanel.removeAll()` removed the buttons from the UI container, but `createdButtons` still held strong references to every `JButton` ever created, preventing garbage collection.
- **Fix:** Clear the `createdButtons` list when removing all buttons from `buttonPanel`.
- **Code Change:** Added `createdButtons.clear();` right after `buttonPanel.removeAll();` inside `removeButton`'s `ActionListener`.
