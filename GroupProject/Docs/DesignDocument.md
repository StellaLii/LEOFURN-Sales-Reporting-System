# Design Document

**Author**: \<Team 181\>

## 1 Design Considerations

### 1.1 Assumptions

- This application is designed for one user per installation only. This application cannot track multiple users' settings.
- No registration and login process for this application.
- All classes in this application will be unit tested.
- This application will run on a phone or tablet device based on Android system.

### 1.2 Constraints

- The user will have only one current job.
- The user will have 100 job offers at maximum.
- If the user wants to use this job comparison system, the user must have at least one current job and one job offer, or more than two job offers. 
- Current job and job offers will be stored in a local database of SQLite
- No connection is needed to an external database.


### 1.3 System Environment

The application must run on Android OS and at least support the OS version "API 29: Android 10.0 (Q)".  The application must interact with a SQLlite database to track the state of our classes between runtimes

## 2 Architectural Design

### 2.1 Component Diagram
![Component](/GroupProject/Design-Team/Image/ComponentDiagram.png)

### 2.2 Deployment Diagram

The application only consists of a database and software, which will run on the same hardware device and we are not interacting with any third party libraries or devices. The database is built into the operating system to which the software is being deployed. This is a simple system with no multitude of computational units. Therefore, the Deployment Diagram is unnecessary.

## 3 Low-Level Design
### 3.1 Class Diagram

![design](/GroupProject/Design-Team/Image/design.png)


## 4 User Interface Design
![UIdesign](/GroupProject/Design-Team/Image/GUI.png)

![UIdesign2](/GroupProject/Design-Team/Image/Menu2.PNG)
