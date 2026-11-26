# Week 2: The Observer Pattern — Practical Activities

**Author:** Michael Kilonzo

Complete practical activities and deliverables for **Week 2: The Observer Pattern - Self-Paced Practical Guide**.

## Summary of Deliverables

| Activity | Deliverable(s) | Learning Outcomes |
| :--- | :--- | :--- |
| **1. Weather Station** | [Observer.java](./activity1-weather-station/Observer.java), [Subject.java](./activity1-weather-station/Subject.java), [WeatherData.java](./activity1-weather-station/WeatherData.java), [CurrentConditionsDisplay.java](./activity1-weather-station/CurrentConditionsDisplay.java), [StatisticsDisplay.java](./activity1-weather-station/StatisticsDisplay.java), [ForecastDisplay.java](./activity1-weather-station/ForecastDisplay.java), [WeatherStationMain.java](./activity1-weather-station/WeatherStationMain.java), [Notes.md](./activity1-weather-station/Notes.md) | #1, #3 |
| **2. Legacy Observer** | [LegacyWeatherData.java](./activity2-legacy-observer/LegacyWeatherData.java), [LegacyDisplay.java](./activity2-legacy-observer/LegacyDisplay.java), [LegacyMain.java](./activity2-legacy-observer/LegacyMain.java), [LegacyLimitations.md](./activity2-legacy-observer/LegacyLimitations.md) | #2 |
| **3. Refactoring Exercise** | [TightlyCoupledSystem.java](./activity3-custom-observer/TightlyCoupledSystem.java), [DataObserver.java](./activity3-custom-observer/DataObserver.java), [DataSubject.java](./activity3-custom-observer/DataSubject.java), [DataModel.java](./activity3-custom-observer/DataModel.java), [LabelDisplay.java](./activity3-custom-observer/LabelDisplay.java), [BarChartDisplay.java](./activity3-custom-observer/BarChartDisplay.java), [TextLogger.java](./activity3-custom-observer/TextLogger.java), [RefactoredMain.java](./activity3-custom-observer/RefactoredMain.java), [ComparisonNotes.md](./activity3-custom-observer/ComparisonNotes.md) | #1, #3 |
| **4. Comparison** | [PropertyChangeDemo.java](./activity4-comparison/PropertyChangeDemo.java), [Comparison.md](./activity4-comparison/Comparison.md), [SelfCheckNotes.md](./activity4-comparison/SelfCheckNotes.md) | #4 |
| **5. Self-Assessment** | [Activity5_SelfAssessment_and_Summary.md](./Activity5_SelfAssessment_and_Summary.md) — 12-question assessment, one-page summary, and final reflection | All (#1–#4) |

## How to Compile and Run (JDK 17+)

```powershell
# Activity 1: Weather Station
javac week02-observer-pattern/activity1-weather-station/*.java
java -cp week02-observer-pattern/activity1-weather-station WeatherStationMain

# Activity 2: Legacy java.util.Observer
javac week02-observer-pattern/activity2-legacy-observer/*.java
java -cp week02-observer-pattern/activity2-legacy-observer LegacyMain

# Activity 3: Tightly Coupled vs. Refactored Observer
javac week02-observer-pattern/activity3-custom-observer/*.java
java -cp week02-observer-pattern/activity3-custom-observer TightlyCoupledSystem
java -cp week02-observer-pattern/activity3-custom-observer RefactoredMain

# Activity 4: PropertyChangeListener Demo
javac week02-observer-pattern/activity4-comparison/*.java
java -cp week02-observer-pattern/activity4-comparison PropertyChangeDemo
```
