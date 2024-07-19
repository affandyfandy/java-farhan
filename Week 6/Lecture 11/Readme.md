# Employee Management System with CRUD, Sorting, and Pagination

#### This guide will walk you through the implementation of an Employee Management System that includes Create, Read, Update, Delete (CRUD) operations, sorting and pagination data.

## Research Composite KEY

In Spring Data JPA, a composite key is a primary key that consists of more than one column and involves using an @Embeddable class to represent the composite key and embedding it into the entity class.. This is useful when you need to uniquely identify a record based on a combination of fields. Here's a step-by-step guide on how to implement composite keys in Spring Data JPA:

For Example [Salary Entity](./assignment1/src/main/java/aliramadahan/assignment/model/Salary.java) & [Salary Id](./assignment1/src/main/java/aliramadahan/assignment/model/key/SalaryId.java)

### `Salary Id`

```java
package aliramadahan.assignment.model.key;

import java.io.Serializable;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class SalaryId implements Serializable {
    @Column(name="emp_no")
    private Integer empNo;

    @Column(name = "from_date")
    private LocalDate fromDate;
}
```

### Explaination

- Annotations:

  - `@Embeddable`: Indicates that this class is a composite key that can be embedded in an entity.
  - `@Column(name="emp_no") and @Column(name = "from_date")`: Specify the column names in the database.

### `Salary`

```java
package aliramadahan.assignment.model;

import aliramadahan.assignment.model.key.SalaryId;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "salaries")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Salary implements Serializable {

    @EmbeddedId
    private SalaryId id;

    @Column(name = "salary", nullable = false)
    private Integer salary;

    @Column(name = "to_date", nullable = false)
    private LocalDate toDate;
}

```

### Explaination

- Annotations:

  - @Entity: Marks this class as a JPA entity.
  - @Table(name = "salaries"): Specifies the table name in the database.
  - @EmbeddedId: Indicates that the field id is an embedded primary key of type SalaryId.
  - @Column(name = "salary", nullable = false): Maps the salary field to the salary column in the database and ensures it's not nullable.
  - @Column(name = "to_date", nullable = false): Maps the toDate field to the to_date column in the database and ensures it's not nullable.

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

#### Employee Entity

1. **List Data**
   ![all](./assets/emp/all.png)
2. **List Page Data**
   ![page](./assets/emp/page.png)
3. **Details Data**
   ![id](./assets/emp/byid.png)
4. **Create Data**
   ![post](./assets/emp/post.png)
5. **Update Data**
   ![update](./assets/emp/update.png)
6. **Delete Data**
   ![delete](./assets/emp/delete.png)

#### Department Entity

1. **List Data**
   ![all](./assets/dep/depall.png)
2. **List Page Data**
   ![page](./assets/dep/deppage.png)
3. **Details Data**
   ![id](./assets/dep/depid.png)
4. **Create Data**
   ![post](./assets/dep/deppots.png)
5. **Update Data**
   ![update](./assets/dep/depupdate.png)
6. **Delete Data**
   ![delete](./assets/dep/depupdate.png)

#### Salary Entity

1. **List Data**
   ![all](./assets/salary/all.png)
2. **List Page Data**
   ![page](./assets/salary/page.png)
3. **Details Data**
   ![id](./assets/salary/byid.png)
4. **Create Data**
   ![post](./assets/salary/post.png)
5. **Update Data**
   ![update](./assets/salary/update.png)
6. **Delete Data**
   ![delete](./assets/salary/delete.png)

#### Titles Entity

1. **List Data**
   ![all](./assets/title/all.png)
2. **List Page Data**
   ![page](./assets/title/page.png)
3. **Details Data**
   ![id](./assets/title/byid.png)
4. **Create Data**
   ![post](./assets/title/post.png)
5. **Update Data**
   ![update](./assets/title/update.png)
6. **Delete Data**
   ![delete](./assets/title/delete.png)
