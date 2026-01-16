<div align="center">

# 🎓 PamakBook Project (Java)
### Final Integrated Social Networking System

[![Java Version](https://img.shields.io/badge/Java-17%2B-orange.svg)](https://www.oracle.com/java/)

[![University](https://img.shields.io/badge/University-UoM-red.svg)](https://www.uom.gr/)

<p align="center">
  <img src="https://img.icons8.com/color/96/000000/java-coffee-cup-logo.png" width="80" alt="Java Logo" />
</p>

</div>

---

## 📖 Project Overview
This is the final evolved version of **PamakBook**, developed for the **Object-Oriented Programming** course at the **University of Macedonia**. The project integrates advanced Java concepts, GUI development, and data persistence.

---

## 📑 Structure & Development Phases

### 🔹 Phase 1: Core Logic
* **User & Groups**: Basic modeling of users and social structures (Standard & Closed Groups).
* **Validation**: Strict academic email verification.
* **Virus Path Tracing**: Logic for identifying potential infection chains.

### 🔹 Phase 2: Social Features & GUI
* **Post System**: Introduction of time-stamped posts and user timelines.
* **Triadic Closure**: Algorithm for suggesting new friends based on mutual connections.
* **Initial GUI**: Login screen and individual user profile pages.

### 🔹 Phase 3: System Integration & Persistence (Current Version)
The final version adds a centralized control layer:
* **Centralized Dashboard**: A main entry window for the entire application.
* **Dynamic User Registration**: GUI-based creation of new users with real-time system updates.
* **Data Serialization**: Implementation of `Serializable` to save and load the entire state of the PamakBook network into a binary file (`PamakBook.ser`).
* **Global infection View**: A specialized dashboard to monitor potential virus transmissions across the whole network.

---

## 🛠️ Technologies & Features
* **Language**: Java 17+.
* **Data Persistence**: Object Serialization for saving/loading system state.
* **UI**: Advanced Java Swing (Main Dashboard, Input Dialogs).
* **Design Patterns**: Composite structures for groups and users.

---

## 📁 Repository Organization
* `Assignment_1_Console/`: Initial business logic.
* `Assignment_2_GUI/`: Graphical interface and posts.
* `Assignment_3_Final/`: Full integration with serialization and main dashboard.
* `resources/`: Contains `t.csv` and other test datasets.

---

<div align="center">

**Maintainer:** [Christos Petridis](https://github.com/XPPET)  
**University:** [Department of Applied Informatics, UoM](https://www.uom.gr/)

</div><div align="center">

# 🎓 PamakBook Project (Java)
### Social Networking System Logic & Data Persistence

[![Java Version](https://img.shields.io/badge/Java-17%2B-orange.svg)](https://www.oracle.com/java/)
[![Persistence](https://img.shields.io/badge/Data-Serialization-blue.svg)]()
[![University](https://img.shields.io/badge/University-UoM-red.svg)](https://www.uom.gr/)

<p align="center">
  <img src="https://img.icons8.com/color/96/000000/java-coffee-cup-logo.png" width="80" alt="Java Logo" />
</p>

</div>

---

## 📖 Project Overview
This project was developed for the **Object-Oriented Programming** course at the **University of Macedonia** for the Academic Year 2025-2026. 

**PamakBook** is a backend-focused social network simulation designed to model user entities and their interactions. Beyond basic connectivity, the system provides advanced functions to identify potential virus transmission channels by assuming that "friends" in a network represent increased social contact.

---

## 📑 Structure & Development Phases

### 🔹 Phase 1: Core Logic & Entity Modeling
Focuses on the core business logic and foundational entities:
* **User Management**: Creation of user profiles with strict academic email validation (ics/iis/dai domains).
* **Friendship Logic**: Methods to manage social connections, ensuring data integrity and identifying mutual friends.
* **Social Groups**: Implementation of standard groups and **Closed Groups**, where enrollment is restricted to friends of existing members.
* **Virus Path Tracing**: A specialized algorithm that maps potential infection chains by traversing friend-of-friend networks.

### 🔹 Phase 2: Content Management & Social Algorithms
Expansion of the system with content entities and network analysis:
* **Post System**: Implementation of social media posts featuring automated timestamps and creator links.
* **Timeline Generation**: Logic for aggregating and sorting posts from a user's social circle chronologically.
* **Triadic Closure**: A "Suggested Friends" algorithm that identifies and proposes new connections based on mutual friendships.

### 🔹 Phase 3: System Integration & Data Persistence
The final stage of development focused on system stability and state preservation:
* **Object Serialization**: Full implementation of the `Serializable` interface to allow the entire network state to be saved to a binary file.
* **Data Persistence**: Mechanisms to ensure all users, groups, and posts are preserved and can be reloaded upon application restart.
* **Global System Management**: Centralized logic for system-wide operations, including new user registration and global infection monitoring.

---

## 🛠️ Technologies & Principles
* **Language**: Java 17+.
* **Core Principles**: Object-Oriented Programming (Inheritance, Polymorphism, Encapsulation).
* **Data Persistence**: Java Object Serialization (`.ser` files).
* **Tools**: Eclipse


---

<div align="center">

**Maintainer:** [Christos Petridis](https://github.com/XPPET)  
**University:** [Department of Applied Informatics, University of Macedonia](https://www.uom.gr/)

</div>
