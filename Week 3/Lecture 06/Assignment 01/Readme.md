# Normalization

## 🧾 Definition

Database normalization is a database design principle for organizing data in an organized and consistent way.

Normalization is a process used in database design to organize data in such a way that it reduces redundancy and dependency. It involves breaking down a database into smaller, more manageable tables and establishing relationships between them.

## ⭐ Purpose of Normalization

The main purpose of database normalization is to avoid complexities, eliminate duplicates, and organize data in a consistent way. In normalization, the data is divided into several tables linked together with relationships.
Database administrators are able to achieve these relationships by using several keys :

- Primary Key, is a column that uniquely identifies the rows of data in that table. It’s a unique identifier such as an employee ID, student ID, voter’s identification number (VIN), and so on.

- Foreign Key is a field that relates to the primary key in another table, like employee_departement

- Composite key is just like a primary key, but instead of having a column, it has multiple columns. like Nim , Email

## 🌀 Types of Database Normalization

---

Normalization works through a series of stages called Normal forms. The normal forms apply to individual relations. The relation is said to be in particular normal form if it satisfies constraints.

| Normal Form | Description                                                                                                               |
| ----------- | ------------------------------------------------------------------------------------------------------------------------- |
| **1NF**     | A relation is in 1NF if it contains atomic values.                                                                        |
| **2NF**     | A relation will be in 2NF if it is in 1NF and all non-key attributes are fully functionally dependent on the primary key. |
| **3NF**     | A relation will be in 3NF if it is in 2NF and no transitive dependency exists.                                            |
| **BCNF**    | A stronger definition of 3NF is known as Boyce-Codd Normal Form (BCNF).                                                   |
| **4NF**     | A relation will be in 4NF if it is in BCNF and has no multi-valued dependency.                                            |
| **5NF**     | A relation is in 5NF if it is in 4NF and does not contain any join dependency, ensuring that joining is lossless.         |

## 1️⃣ First Normal Form (1NF)

The table 1NF if:

- Each column contains atomic values.
- Each column contains values of a single type.
- Each column has a unique name.
- The order in which data is stored does not matter.
- There are no repeating groups or arrays, and each field contains only one value.

## 2️⃣ Second Normal Form (2NF)

The table is 2NF if:

- Already in 1NF
- All non-key attributes are completely dependent on the primary key.

It means that every attribute in the table must depend directly on the primary key

## 3️⃣ Third Normal Form (3NF)

The table is 3NF if:

- Already in 2NF
- All non-key attributes must not depend transitively on the primary key **(no transitive dependency)**.

This means that every non-key attribute must depend directly on the primary key and no non-key attribute must depend on another non-key attribute that depends on the primary key.

## Example

---

## Unnormalized Table

| StudentID | StudentName | CourseID | CourseName1 | CourseName2 |
| --------- | ----------- | -------- | ----------- | ----------- |
| 1         | Alice       | C1,C2    | Math        | Biology     |
| 2         | Bob         | C1       | Math        | Chemical    |
| 1         | Alice       | C2       | Physics     | Social      |
| 3         | Charlie     | C1,C3    | Math        | Music       |
| 2         | Bob, Alissa | C2       | Physics     | Sport       |

## 1NF

- Each column have to contain atomic (indivisible) values.
- Each attribute unique (no multivalued attributes)

So based on 1NF , table become :
| StudentID | StudentName | CourseID | CourseName |
|-----------|-------------|----------|------------|
| 1 | Alice | C1 | Math |
| 1 | Alice | C2 | Biology |
| 1 | Alice | C2 | Physics |
| 1 | Alice | C2 | Social |
| 2 | Bob | C1 | Math |
| 2 | Bob | C2 | Physics |
| 2 | Alissa | C2 | Physics |
| 2 | Bob | C2 | Sport |
| 3 | Charlie | C1 | Math |
| 3 | Charlie | C3 | Music |

## 2NF

Ensure that all attributes are fully functionally dependent on the primary key.
Based on 1NF table, the primary could be (StudentID, CourseID).
So based on 2NF, the tables become:

Student Table:

| StudentID | StudentName |
| --------- | ----------- |
| 1         | Alice       |
| 2         | Bob         |
| 2         | Alissa      |
| 3         | Charlie     |

Course Table :
| CourseID | CourseName |
|----------|------------|
| C1 | Math |
| C2 | Physics |
| C2 | Social |
| C1 | Chemical |
| C3 | Music |

Enrollment Table :
| StudentID | CourseID |
|-----------|----------|
| 1 | C1 |
| 1 | C2 |
| 1 | C1 |
| 1 | C2 |
| 2 | C1 |
| 2 | C2 |
| 2 | C2 |
| 2 | C1 |
| 3 | C1 |
| 3 | C3 |

## 3NF
In 3NF, the table must first be in 2NF and no transitive dependencies should exist. Here, CourseName depends only on CourseID.

Student Table :
| StudentID | StudentName |
|-----------|-------------|
| 1 | Alice |
| 2 | Bob |
| 2 | Alissa |
| 3 | Charlie |

Course Table:
| CourseID | CourseName |
|----------|------------|
| C1 | Math |
| C2 | Physics |
| C2 | Social |
| C1 | Chemical |
| C3 | Music |

Enrollment Table:
| StudentID | CourseID |
|-----------|----------|
| 1 | C1 |
| 1 | C2 |
| 1 | C1 |
| 1 | C2 |
| 2 | C1 |
| 2 | C2 |
| 2 | C2 |
| 2 | C1 |
| 3 | C1 |
| 3 | C3 |

## Advantages of Normal Form

---

- Reduced data redundancy: Normalization helps to eliminate duplicate data in tables, reducing the amount of storage space needed and improving database efficiency.

- Improved data consistency: Normalization ensures that data is stored in a consistent and organized manner, reducing the risk of data inconsistencies and errors.

- Simplified database design: Normalization provides guidelines for organizing tables and data relationships, making it easier to design and maintain a database.
- Improved query performance: Normalized tables are typically easier to search and retrieve data from, resulting in faster query performance.
- Easier database maintenance: Normalization reduces the complexity of a database by breaking it down into smaller, more manageable tables, making it easier to add, modify, and delete data.
