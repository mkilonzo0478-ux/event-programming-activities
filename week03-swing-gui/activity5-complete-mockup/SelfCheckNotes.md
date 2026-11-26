# Activity 5: Self-Check Notes — Student Portal Mockup

**Author:** Michael Kilonzo

## Self-Check Questions & Answers

1. **How many different layout managers did you use in this mockup?**
   - Four layout managers working together:
     - `BorderLayout` for the main `JFrame`, the `header` panel, the `statusBar` panel, and each stat card.
     - `GridLayout` for the 6-row `sidebar` navigation panel and the 1x3 `cardsPanel`.
     - `BoxLayout` (`Y_AXIS`) for vertically stacking the welcome heading, stat cards, and notifications list in the `content` panel.
     - `FlowLayout` (default inside `JMenuBar` / standard panels).

2. **Why do we use `Box.createVerticalStrut(20)` between components?**
   - `Box.createVerticalStrut(20)` inserts an invisible, fixed-height 20-pixel spacer inside a `BoxLayout`, creating clean vertical breathing room without adding empty filler panels or per-component borders.

3. **What is the purpose of `setPreferredSize()` on the sidebar?**
   - Setting `sidebar.setPreferredSize(new Dimension(180, 0))` gives the `BorderLayout.WEST` region a consistent 180-pixel width; without it, `BorderLayout` shrinks the sidebar down to the narrowest width needed by its button labels.

## Customizations Applied (Step 5.3)

- Customized title and branding to `"Rift Valley University — Student Portal"`.
- Personalized welcome banner and status bar for `"Michael Kilonzo"`.
- Updated stat cards (`GPA: 3.82`, `Units: 52 / 56`, `Fees: Paid`).
- Added a `JMenuBar` (`Portal`, `Academics`, `Help`) via `frame.setJMenuBar(...)`.
