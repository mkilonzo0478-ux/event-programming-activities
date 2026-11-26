# Activity 2: Self-Check Notes — Basic Swing Components Tour

**Author:** Michael Kilonzo

## Self-Check Questions & Answers

1. **What is the difference between `JTextField` and `JTextArea`?**
   - `JTextField` is designed for single-line text input (such as usernames, emails, or search queries), whereas `JTextArea` supports multi-line text input (such as comments, addresses, or descriptions).

2. **Why do we put `JRadioButton`s inside a `ButtonGroup`?**
   - A `ButtonGroup` enforces mutual exclusion so that only one radio button in the group can be selected at a time. Without a `ButtonGroup`, multiple radio buttons could be selected simultaneously.

3. **Why does the `JTextArea` need a `JScrollPane`?**
   - `JTextArea` does not provide built-in scrollbars. Wrapping it inside a `JScrollPane` automatically displays vertical/horizontal scrollbars whenever the text overflows the visible area.
