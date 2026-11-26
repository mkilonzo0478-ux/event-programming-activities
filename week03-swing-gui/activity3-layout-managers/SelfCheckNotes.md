# Activity 3: Self-Check Notes — Layout Managers Deep Dive

**Author:** Michael Kilonzo

## Self-Check Questions & Answers

1. **When would you choose `GridLayout` over `FlowLayout`?**
   - Use `GridLayout` when you need equal-sized cells arranged in a fixed number of rows and columns (such as a calculator keypad or a uniform card/photo grid). Use `FlowLayout` when components should keep their preferred sizes and flow naturally left-to-right, wrapping onto the next line when space runs out (such as toolbars).

2. **Why do we often nest layout managers?**
   - No single layout manager handles every section of a realistic desktop UI cleanly. Nesting `JPanel` containers — each configured with the layout manager best suited to its region — lets you compose toolbars (`FlowLayout`), navigation sidebars (`GridLayout`), vertical content stacks (`BoxLayout`), and main window regions (`BorderLayout`).

3. **What does `BorderLayout.CENTER` do differently from the other regions?**
   - `BorderLayout.CENTER` expands both horizontally and vertically to fill all remaining space left over by `NORTH`, `SOUTH`, `EAST`, and `WEST`, whereas the four outer regions only take the space required by their preferred widths/heights.
