# Activity 3: "Procedural vs. Event-Driven" — Side-by-Side Coding

**Author:** Michael Kilonzo

**Learning Outcome Addressed:** #2 (Compare and contrast programming paradigms)

---

## Step 2 Notes: Procedural Calculator (`ProceduralCalculator.java`)

1. **What is the exact order of steps in this program?**
   1. Print the banner (`=== Procedural Calculator ===`).
   2. Prompt for and read the first number (`num1`).
   3. Prompt for and read the operator (`operator`).
   4. Prompt for and read the second number (`num2`).
   5. Compute `result` in a `switch` statement (checking for division by zero or invalid operator).
   6. Print the formatted result, close the `Scanner`, and exit `main`.

2. **What happens if the user enters the wrong input at step 2?**
   - If the user enters an unrecognized operator (e.g., `%` or `abc`), the program still asks for the second number first, then hits `default:` in the `switch`, prints `"Invalid operator"`, closes the scanner, and terminates immediately. If the user enters non-numeric text at step 1 or step 3, `scanner.nextDouble()` throws an `InputMismatchException` and crashes.

3. **Can the user calculate again without restarting the program?**
   - **No.** After printing one result (or error), `main()` reaches the end and exits. The user must re-run the program to perform another calculation.

4. **Who controls the flow of this program — the user or the program?**
   - **The program** controls the flow strictly from top to bottom. The user can only respond when and in the exact order the program asks for input.

---

## Step 3 Notes: Event-Driven Calculator (`EventDrivenCalculator.java`)

1. **Can the user click buttons in any order?**
   - **Yes.** Once the GUI window is visible, the user can press digits, switch operators, hit `C` to clear, or press `=` at any time without following a forced prompt sequence.

2. **What happens if the user makes a mistake?**
   - The user can click **`C`** (Clear) at any moment to reset `display` to `"0"`, `firstOperand` to `0`, and `operator` to `""`, or simply pick a different operator before typing the second number, without crashing or closing the application. (Similarly, dividing by zero displays `"Error"` and lets the user continue cleanly.)

3. **Can the user calculate again without restarting?**
   - **Yes.** The Swing Event Dispatch Thread (EDT) keeps the application alive in its event loop until the user explicitly closes the window (`EXIT_ON_CLOSE`), allowing unlimited calculations in one session.

4. **Who controls the flow — the user or the program?**
   - **The user** controls the flow (inversion of control). The program initializes the interface and waits passively for user events to trigger handlers.

5. **How many event handlers are in this program?**
   - There are **16 button `ActionListener` handlers** registered at runtime across the 4×4 grid (created via 4 factory methods: 10 number buttons in `createNumberButton`, 4 operator buttons in `createOperatorButton`, 1 equals button in `createEqualsButton`, and 1 clear button in `createClearButton`), plus the `SwingUtilities.invokeLater` startup runnable.

---

## Step 4: Completed Comparison Table

| ASPECT | PROCEDURAL | EVENT-DRIVEN |
| :--- | :--- | :--- |
| **Who controls the flow?** | The program (fixed top-to-bottom sequence) | The user (via events dispatched by the event loop) |
| **Can user make mistakes and recover easily?** | No — invalid input either crashes or exits immediately | Yes — user can press Clear (`C`) or choose another action without restarting |
| **Can user interact in any order?** | No — input must match the exact prompt sequence | Yes — any enabled button/component can be triggered at any time |
| **How many possible execution paths?** | Very few, linear and predictable (main path + error branches) | Virtually unlimited, depending on the sequence of user events |
| **Code organization** | Sequential blocks inside `main()` | Modular UI setup + small, independent listener/handler callbacks tied to state |
| **User experience** | Rigid, console-based, single-use per run | Interactive, visual, forgiving, and continuously responsive |

---

## Step 5: Written Reflection

The primary difference between procedural and event-driven programming lies in who dictates the order of execution. In the procedural calculator, the program follows a rigid, predetermined sequence from top to bottom, forcing the user to supply inputs in one exact order and terminating after a single calculation. By contrast, the event-driven calculator sets up its visual components and state variables, then hands control over to the Swing event loop, which waits for user actions such as button clicks. For interactive applications, the event-driven approach is far superior because users expect freedom to click controls in any order, correct mistakes on the fly, and perform repeated tasks without restarting. Event-driven programming also aligns naturally with graphical user interfaces, where dozens of buttons, menus, and text fields must remain simultaneously ready to respond independently. Separating UI layout from small, focused event handlers makes interactive software much easier to extend and maintain.
