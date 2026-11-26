# Week 3: Building GUI Applications with Swing

**Author:** Michael Kilonzo

Complete practical activities and deliverables for **Week 3 Self-Paced Guide: Building GUI Applications with Swing**.

## Summary of Deliverables

| Activity | Deliverable(s) | Learning Outcomes |
| :--- | :--- | :--- |
| **1. First Swing Window** | [FirstWindow.java](./activity1-first-window/FirstWindow.java), [SelfCheckNotes.md](./activity1-first-window/SelfCheckNotes.md) | #1, #3 |
| **2. Basic Components Tour** | [ComponentShowcase.java](./activity2-basic-components/ComponentShowcase.java), [SelfCheckNotes.md](./activity2-basic-components/SelfCheckNotes.md) | #2 |
| **3. Layout Managers Deep Dive** | [FlowLayoutDemo.java](./activity3-layout-managers/FlowLayoutDemo.java), [BorderLayoutDemo.java](./activity3-layout-managers/BorderLayoutDemo.java), [GridLayoutDemo.java](./activity3-layout-managers/GridLayoutDemo.java), [BoxLayoutDemo.java](./activity3-layout-managers/BoxLayoutDemo.java), [CombinedLayoutDemo.java](./activity3-layout-managers/CombinedLayoutDemo.java), [SelfCheckNotes.md](./activity3-layout-managers/SelfCheckNotes.md) | #2 |
| **4. The EDT Rule** | [FreezeDemo.java](./activity4-edt-rule/FreezeDemo.java), [FixedDemo.java](./activity4-edt-rule/FixedDemo.java), [ObservationNotes.md](./activity4-edt-rule/ObservationNotes.md) | #3 |
| **5. Complete Static Mockup** | [StudentPortal.java](./activity5-complete-mockup/StudentPortal.java), [SelfCheckNotes.md](./activity5-complete-mockup/SelfCheckNotes.md) | #4 |
| **Final Self-Assessment** | [FinalSelfAssessment.md](./FinalSelfAssessment.md) | All (#1–#4) |

## How to Compile and Run (JDK 8+)

```powershell
# Activity 1: First Window
javac week03-swing-gui/activity1-first-window/FirstWindow.java
java -cp week03-swing-gui/activity1-first-window FirstWindow

# Activity 2: Basic Components Showcase
javac week03-swing-gui/activity2-basic-components/ComponentShowcase.java
java -cp week03-swing-gui/activity2-basic-components ComponentShowcase

# Activity 3: Layout Managers Demos
javac week03-swing-gui/activity3-layout-managers/*.java
java -cp week03-swing-gui/activity3-layout-managers FlowLayoutDemo
java -cp week03-swing-gui/activity3-layout-managers BorderLayoutDemo
java -cp week03-swing-gui/activity3-layout-managers GridLayoutDemo
java -cp week03-swing-gui/activity3-layout-managers BoxLayoutDemo
java -cp week03-swing-gui/activity3-layout-managers CombinedLayoutDemo

# Activity 4: EDT Rule (Freezing vs. Responsive)
javac week03-swing-gui/activity4-edt-rule/*.java
java -cp week03-swing-gui/activity4-edt-rule FreezeDemo
java -cp week03-swing-gui/activity4-edt-rule FixedDemo

# Activity 5: Complete Student Portal Mockup
javac week03-swing-gui/activity5-complete-mockup/StudentPortal.java
java -cp week03-swing-gui/activity5-complete-mockup StudentPortal
```
