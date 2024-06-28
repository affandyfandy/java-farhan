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

Normalization works through a series of stages called Normal forms. The normal forms apply to individual relations. The relation is said to be in particular normal form if it satisfies constraints.

| Normal Form | Description                                                                                                               |
| ----------- | ------------------------------------------------------------------------------------------------------------------------- |
| **1NF**     | A relation is in 1NF if it contains atomic values.                                                                        |
| **2NF**     | A relation will be in 2NF if it is in 1NF and all non-key attributes are fully functionally dependent on the primary key. |
| **3NF**     | A relation will be in 3NF if it is in 2NF and no transitive dependency exists.                                            |
| **BCNF**    | A stronger definition of 3NF is known as Boyce-Codd Normal Form (BCNF).                                                   |
| **4NF**     | A relation will be in 4NF if it is in BCNF and has no multi-valued dependency.                                            |
| **5NF**     | A relation is in 5NF if it is in 4NF and does not contain any join dependency, ensuring that joining is lossless.         |

## Advantages of Normal Form

- Reduced data redundancy: Normalization helps to eliminate duplicate data in tables, reducing the amount of storage space needed and improving database efficiency.

- Improved data consistency: Normalization ensures that data is stored in a consistent and organized manner, reducing the risk of data inconsistencies and errors.

- Simplified database design: Normalization provides guidelines for organizing tables and data relationships, making it easier to design and maintain a database.
- Improved query performance: Normalized tables are typically easier to search and retrieve data from, resulting in faster query performance.
- Easier database maintenance: Normalization reduces the complexity of a database by breaking it down into smaller, more manageable tables, making it easier to add, modify, and delete data.
