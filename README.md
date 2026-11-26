# Week 1: Event-Driven Programming in Java

**Author:** Michael Kilonzo

Complete practical activities and deliverables for **Week 1: Self-Paced Practical Activities for Event-Driven Programming**.

## Summary of Deliverables

| Activity | Deliverable(s) | Learning Outcomes |
| :--- | :--- | :--- |
| **1** | [Activity1_EDP_Components.md](./Activity1_EDP_Components.md) — 9 real-world systems analyzed + self-check | #1, #4 |
| **2** | [Activity2_EventLoop_Reflection.md](./Activity2_EventLoop_Reflection.md) — Blocked event loop answers + reflection | #3 |
| **3** | [ProceduralCalculator.java](./Week1_EDP/procedural/ProceduralCalculator.java), [EventDrivenCalculator.java](./Week1_EDP/eventdriven/EventDrivenCalculator.java), [Activity3_Comparison_and_Reflection.md](./Week1_EDP/Activity3_Comparison_and_Reflection.md) | #2 |
| **4** | [Bug1_BlockingEDT.java](./BugFixChallenge/Bug1_BlockingEDT.java), [Bug2_MissingListener.java](./BugFixChallenge/Bug2_MissingListener.java), [Bug3_WrongThread.java](./BugFixChallenge/Bug3_WrongThread.java), [Bug4_MemoryLeak.java](./BugFixChallenge/Bug4_MemoryLeak.java), [BugFixes.md](./BugFixChallenge/BugFixes.md) | #1, #3 |
| **5** | [SystemDesign.md](./Activity5_SmartHome/SystemDesign.md), [SmartHomePrototype.java](./Activity5_SmartHome/SmartHomePrototype.java) (Smart Home Controller) | #1, #2, #3, #4 |
| **6** | [Activity6_SelfAssessment_and_Summary.md](./Activity6_SelfAssessment_and_Summary.md) — 15-question self-assessment + Week 1 summary | All |

## How to Compile and Run (JDK 8+)

```bash
# Activity 3: Procedural Calculator
javac Week1_EDP/procedural/ProceduralCalculator.java
java -cp Week1_EDP/procedural ProceduralCalculator

# Activity 3: Event-Driven Calculator
javac Week1_EDP/eventdriven/EventDrivenCalculator.java
java -cp Week1_EDP/eventdriven EventDrivenCalculator

# Activity 4: Bug Fix Challenge
javac BugFixChallenge/*.java
java -cp BugFixChallenge Bug1_BlockingEDT
java -cp BugFixChallenge Bug2_MissingListener
java -cp BugFixChallenge Bug3_WrongThread
java -cp BugFixChallenge Bug4_MemoryLeak

# Activity 5: Smart Home Controller Prototype
javac Activity5_SmartHome/SmartHomePrototype.java
java -cp Activity5_SmartHome SmartHomePrototype
```

---

## Week 2: The Observer Pattern

See [week02-observer-pattern/README.md](./week02-observer-pattern/README.md) for all Week 2 activities, compile/run instructions, comparison notes, and self-assessment deliverables.

