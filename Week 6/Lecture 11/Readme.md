# Employee Management System with CRUD, Sorting, and Pagination

#### This guide will walk you through the implementation of an Employee Management System that includes Create, Read, Update, Delete (CRUD) operations, sorting and pagination data.

## Project Structure

```bash
assignment
├── .mvn/wrapper/
│   └── maven-wrapper.properties
├── src/main/
│   ├── java/aliramadhan/assignment
│   │   ├── controller/
│   │   │   └── DepartmentController.java
│   │   ├── model/
│   │   │   └── key/
|   │   │   │    └── DeptEmpId.java
|   │   │   │    └── DeptManagerId.java
|   │   │   │    └── SalaryId.java
|   │   │   │    └── TitleId.java
│   │   │   └── Department.java
│   │   │   └── DeptEmployee.java
│   │   │   └── DeptManager.java
│   │   │   └── Employee.java
│   │   │   └── Salary.java
│   │   │   └── Title.java
│   │   ├── repository/
│   │   │   └── Department.java
│   │   │   └── DepartmentRepository.java
│   │   │   └── DeptEmployeeRepository.java
│   │   │   └── DeptManagerRepository.java
│   │   │   └── EmployeeRepository.java
│   │   │   └── SalaryRepository.java
│   │   │   └── TitleRepository.java
│   │   ├── service/
|   │   │   ├── impl/
|   │   │   │   └── DepertmentServiceImpl.java
│   │   │   └── DepertmentService.java
│   │   └── AssignmentApplication.java
│   └── resources/
│       └── application.properties
├── .gitignore
├── mvnw
├── mvnw.cmd
└── pom.xml
```

## SQL Query

### 🧩 SQL Query Data

Here is the SQL query to create the database, table, and instantiate some data.

````sql
-- Create the database
CREATE DATABASE employeeApp;

-- Use the database
USE employeeApp;

-- Create the employee table
...

## Config Project

### `Application Properties`

Dont forget to configure [application properties](./assignment/src/main/resources/application.properties) with this format

```java
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/<your_database>
spring.datasource.username=<your_user_name>
spring.datasource.password=<your_password>
spring.jpa.hibernate.ddl-auto=update
````

### `Pom.xml`

Dont forget to configure dependency

```xml
<dependencies>
    <!-- JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <!-- Devtools for enable reload -->
    <dependency>
    <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <!-- Connectore -->
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <scope>runtime</scope>
    </dependency>
    <!-- Lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

### 📸 Results

Program can CRUD of the data, mapping with sort and pagination.
Here's the documentation.

1. **List Department Data**
...