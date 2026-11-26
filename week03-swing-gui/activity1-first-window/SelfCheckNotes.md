# Activity 1: Self-Check Notes — Your First Swing Window

**Author:** Michael Kilonzo

## Self-Check Questions & Answers

1. **What is the difference between `JFrame` and the content pane?**
   - `JFrame` is the top-level window container (including the title bar, window borders, and minimize/maximize/close controls). The content pane is the container inside the `JFrame` where application components are placed. In modern Swing, calling `frame.add(component)` delegates automatically to `frame.getContentPane().add(component)`.

2. **What does `EXIT_ON_CLOSE` do?**
   - It configures the close button (`X`) to dispose the frame and terminate the JVM (`System.exit(0)`). Other close options include `DO_NOTHING_ON_CLOSE`, `HIDE_ON_CLOSE` (the default), and `DISPOSE_ON_CLOSE`.

3. **Why did we wrap the code in `SwingUtilities.invokeLater()`?**
   - Swing components are not thread-safe, so all GUI creation and updates must execute on the Event Dispatch Thread (EDT). `SwingUtilities.invokeLater()` schedules the window creation lambda onto the EDT (`AWT-EventQueue-0`).

## Experiment Notes (Step 1.4)

- Verified `Thread.currentThread().getName()` prints `AWT-EventQueue-0`, confirming execution on the EDT.
- Explored `setTitle(...)`, `setSize(800, 600)`, `setLocation(100, 100)`, and `setResizable(false)` behavior.
