<div align="center">

# 🎓 PamakBook Project (Java)
### Social Networking & Contact Tracing System

[![Java Version](https://img.shields.io/badge/Java-17%2B-orange.svg)](https://www.oracle.com/java/)

[![University](https://img.shields.io/badge/University-UoM-red.svg)](https://www.uom.gr/)

<p align="center">
  <img src="https://img.icons8.com/color/96/000000/java-coffee-cup-logo.png" width="80" alt="Java Logo" />
</p>

</div>

---

## 📖 Description
This project was developed for the **Object-Oriented Programming** course at the **University of Macedonia** (Academic Year 2025-2026). 

**PamakBook** is a social network designed to model user entities and their interactions. Beyond basic connectivity, the system provides functions to identify potential virus transmission channels by assuming that "friends" in a network represent increased social contact.

---

## 📑 Structure & Development Phases

### 🔹 Phase 1: Modeling & Console App
Focuses on the core logic and entity modeling:
* **User Management**: Handling users with strict email validation for `ics`, `iis`, and `dai` domains.
* **Friendship Logic**: Methods to check, add, and print friends, ensuring a user cannot be friends with themselves.
* **Social Groups**: Creation of standard groups and **Closed Groups**, where enrollment requires the user to be a friend of an existing member.
* **Virus Path Tracing**: An algorithm that returns all friends and friends-of-friends to identify potential infection paths.

### 🔹 Phase 2: GUI & Extended Features
Expansion of the application with a graphical user interface and advanced social features:
* **Post System**: Users can create posts with text and automated Date timestamps.
* **Suggested Friends**: Implementation of the **Triadic Closure** principle, suggesting new friends based on mutual connections.
* **Java Swing GUI**:
    * **Login Screen**: Authentication by entering a username.
    * **User Page**: Displays user info, a chronological timeline of friends' posts, and suggested friends.
    * **Infection Window**: Displays a list of users requiring testing if the current user is infected.

---

## 🛠️ Technologies & Principles
* **Language**: Java 17+.
* **UI Framework**: Java Swing.
* **Design Principles**: Object-Oriented modeling (Inheritance, Polymorphism, Encapsulation).
* **Tools**: Eclipse / IntelliJ IDEA.

---

## 📁 File Organization
* `src/`: Contains all `.java` source files.
* `docs/`: Assignment requirements and documentation.

---

<div align="center">

**Maintainer:** [Christos Petridis](https://github.com/XPPET)  
**University:** [Department of Applied Informatics, UoM](https://www.uom.gr/)

</div>
