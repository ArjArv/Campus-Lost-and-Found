OVERVIEW:
A Java console-based application to manage lost and found items within a campus environment. 
Supports reporting, searching, updating, deleting, and verifying items with role-based access (Student vs. Staff/Admin).

PROJECT STRUCTURE:
src/
 ├── model/          # Core data models (User, Student, Staff, Item)
 ├── repository/     # Data persistence (ItemRepository, UserRepository)
 ├── service/        # Business logic (ItemService, UserService, Validation, Exceptions)
 ├── menu/           # Console menus (Menu for students, AdminMenu for staff)
 └── Main.java       # Entry point

FEATURES:
User Registration & Login (Student/Staff roles)
Admin Dashboard (verify items, manage users)
Report Lost/Found Items
Search Items (by ID or keyword)
Update/Claim Item Status
Delete Items
List All Items
Persistence (data stored in backup.txt and users.txt)
Validation & Error Handling
Performance Optimization (efficient search, buffered I/O)

HOW TO RUN:
i) Clone the Repo: 
git clone https://github.com/ArjArv/Campus-Lost-and-Found.git
cd Campus-Lost-and-Found/src
ii) Compile:
javac model/*.java repository/*.java service/*.java menu/*.java Main.java
iii) Run:
java Main

DEMO FLOW:
Register a new student → report lost item → search by keyword.
Login as staff → verify item → list all items.
Check persistence in backup.txt and users.txt.

FUTURE SCOPE:
Database integration (MySQL/JDBC)
Notifications (Email/SMS)
GUI/Web interface (JavaFX/Spring Boot)
Advanced security (password hashing, RBAC)
