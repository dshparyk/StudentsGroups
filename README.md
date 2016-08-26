# StudentsGroups
This project is called ’Student and Groups’.
It is a simple version of MVC project I have started to practise my knowledge of different java technologies.

IDE: Eclipse
DB: MySQL
VCS: Git
SevletContainer: Tomcat

This project was written with the following steps:
- 3 classes where created: Student(with student fields, getters and setters), Group(with group fields, getters and setters), ManagementSystem(students and groups lists, add\delete\update student, move students from one group to another, delete all students from group).
- Added simple UI using SWING. New class StudentsFrame was added to another package.
- Moved data from Collections to DB. Using MySQL, JDBC.
- Added listeners to UI 
- Updated UI. Added new class StudentTableModel to use with DB
- Updated UI. Added StudentDialog class(new window for entering students data) and GroupDialog class(new window for moving students)
- Moving to the Web. Started on Tomcat server and used simple Servlet to show table with groups data from DB. Left frame package used for UI just for reference.
- Added JSP with CustomTag
- Added JNDI. Changed web.xml and added context.xml(for automated DB connection). Updated ManagementSystem. Added MainFrameServlet, StudentFrameServlet, MainFrameForm, StudentForm, MainFrame.jsp, StudentFrame.jsp. Started using EL for JSP.
