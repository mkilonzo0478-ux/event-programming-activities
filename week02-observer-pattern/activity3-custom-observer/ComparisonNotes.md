# Activity 3: Coupling Analysis & Refactoring Comparison Notes

**Author:** Michael Kilonzo

## Step 2: Identifying the Coupling Problem (Before Refactoring)

1. **What happens if we want to add a third display class? Do we have to modify `DataModel`?**
   - Yes. In `TightlyCoupledSystem.DataModel.setValue(int value)`, `DataModel` directly instantiates `new LabelDisplay().showValue(value)` and `new BarChartDisplay().drawBar(value)`. Adding a third display forces us to open and edit `DataModel` itself (violating the Open/Closed Principle).

2. **What happens if `LabelDisplay` is deleted or renamed?**
   - `DataModel` immediately fails to compile (`cannot find symbol: class LabelDisplay`) because `DataModel` holds a hard compile-time dependency on the concrete `LabelDisplay` class and its specific `showValue(int)` method name.

3. **Can we easily test `DataModel` in isolation?**
   - No. Because `DataModel.setValue(...)` hard-codes concrete UI display classes, testing `DataModel` requires those display classes to exist and execute their side effects every time `setValue` is called, making isolated unit testing or mocking difficult.

---

## Step 4: Comparison Between Tightly Coupled and Observer Pattern

| Aspect | Tightly Coupled (`TightlyCoupledSystem`) | Observer Pattern (`DataModel` + `DataObserver`) |
| :--- | :--- | :--- |
| **Adding a new display** | Modify `DataModel` directly | Implement `DataObserver` (e.g., `TextLogger`) and call `model.addObserver(...)` without touching `DataModel` |
| **Testing `DataModel` in isolation** | Hard — it depends directly on concrete display classes | Easy — `DataModel` can be tested with zero observers or with lightweight mock/lambda `DataObserver`s |
| **Removing a display** | Modify `DataModel` and recompile it | Call `model.removeObserver(...)` at runtime or simply don't register it |
| **Dependency direction** | `DataModel` depends on every concrete UI class | `DataModel` depends only on the `DataObserver` interface; concrete displays depend on the abstraction |
| **Runtime flexibility** | Fixed at compile time | Observers can subscribe and unsubscribe dynamically at runtime |
