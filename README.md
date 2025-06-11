# EmployeeMS - Employee Management System
<!-- Repository Name: EmployeeMS -->

## Project Description
EmployeeMS is a comprehensive web-based Employee Management System that allows organizations to efficiently manage employee information. The system provides a user-friendly interface for administrators to perform CRUD (Create, Read, Update, Delete) operations on employee records, including personal details and profile images.

## Features
- **User Authentication**: Secure login and registration system
- **Employee Dashboard**: View all employees in a tabular format
- **Employee Management**:
  - Add new employees with details (name, email, age, address)
  - Upload and store employee profile images
  - Update existing employee information
  - Delete employee records
- **Responsive Design**: Works on various screen sizes using Bootstrap

## Technologies Used
### Backend
- Java EE (Jakarta EE)
- Servlets for HTTP request handling
- JDBC for database operations
- MySQL database for data storage
- Connection pooling with Apache Commons DBCP
- JSON processing with Jackson

### Frontend
- HTML5, CSS3
- Bootstrap 5 for responsive design
- JavaScript/jQuery for dynamic content and AJAX calls

## Setup Instructions
1. **Prerequisites**:
   - JDK 17 or higher
   - Apache Tomcat 10 or higher
   - MySQL 8.0 or higher
   - Maven

2. **Database Setup**:
   - Create a MySQL database named `ems_db`
   - Create a table named `employed` with the following structure:
     ```sql
     CREATE TABLE employed (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(100) NOT NULL,
       email VARCHAR(100) NOT NULL,
       age INT,
       address VARCHAR(255),
       image LONGBLOB
     );
     ```
   - Create a user table for authentication

3. **Configuration**:
   - Update the database connection details in `web/META-INF/context.xml`

4. **Build and Deploy**:
   - Build the project using Maven: `mvn clean package`
   - Deploy the generated WAR file to Tomcat

## Usage
1. Access the application at `http://localhost:8080/EMS_Web_exploded/`
2. Register a new account or login with existing credentials
3. Use the dashboard to view, add, update, or delete employee records
4. Upload employee profile images as needed
5. Logout when finished

## License
This project is licensed under the MIT License - see the LICENSE file for details.