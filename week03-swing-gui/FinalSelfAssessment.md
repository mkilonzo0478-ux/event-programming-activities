# Week 3: Final Self-Assessment & Summary

**Author:** Michael Kilonzo

---

## Learning Outcomes Checklist

- [x] **Outcome 1:** Create a functional Swing application with a main window (`JFrame`)
- [x] **Outcome 2:** Add, customize, and arrange GUI components using layout managers
- [x] **Outcome 3:** Ensure GUI creation and updates happen on the Event Dispatch Thread (EDT)
- [x] **Outcome 4:** Build a static, non-interactive user interface layout

---

## Final Self-Assessment Answers

### Learning Outcome 1: Create a functional Swing application with a main window
- **Self-Rating:** 5 / 5
- **Minimal code from memory to create a `JFrame`:**

```java
import javax.swing.*;

public class MinimalWindow {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("My Window");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 400);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
```

---

### Learning Outcome 2: Add, customize, and arrange components using layouts
- **Self-Rating:** 5 / 5
- **Four layout managers and one use case each:**

| Layout | Use Case |
| :--- | :--- |
| `FlowLayout` | Top toolbars or wrapping rows of action buttons |
| `BorderLayout` | Main application window shell (header `NORTH`, sidebar `WEST`, content `CENTER`, status bar `SOUTH`) |
| `GridLayout` | Equal-sized cells such as calculator keypads, sidebar button stacks, or dashboard stat card rows |
| `BoxLayout` | Vertical or horizontal stacking of heterogeneous sections with `Box.createVerticalStrut(...)` spacing |

---

### Learning Outcome 3: Ensure GUI code runs on the EDT
- **Self-Rating:** 5 / 5
- **Two golden rules of Swing threading:**
  1. **All GUI creation and updates must happen on the Event Dispatch Thread (EDT)** using `SwingUtilities.invokeLater(...)`.
  2. **Never perform long-running or blocking operations on the EDT**; offload them to a background `Thread` (or `SwingWorker`) and dispatch final UI updates back onto the EDT via `SwingUtilities.invokeLater(...)`.

---

### Learning Outcome 4: Build a static, non-interactive UI layout
- **Self-Rating:** 5 / 5
- **Did `StudentPortal.java` run successfully?**
  - [x] **Yes** — Compiled cleanly with `javac` and launched the full customized University Student Portal window on `AWT-EventQueue-0` without errors.
