# Follow the example

## `pre-requirement`

- JDK : 18.0.2.1
  - ![jdk version](/Assignment%2001/assets/jdk.png)
- Database : mysql (latest version)
  - ![mysql version](/Assignment%2001//assets/mysql.png)
- IDE : intelij

## Create Spring Project

![Init project](/Assignment%2001//assets/initproject.png) with springinitializr

## Maven Dependencies

![Dependencies](/Assignment%2001//assets/dependencies.png)

## Create Database

### Create Database and Table

![Init database](/Assignment%2001//assets/db2.png)

### Result

![Init database](/Assignment%2001//assets/db1.png)

## Configure Properties

![Configure properties](/Assignment%2001//assets/properties.png)

## Create Model

![Model](/Assignment%2001//assets/model.png)

## Create Repository

![Repository](/Assignment%2001//assets/repo.png)

## Create Controller

- Package

  - ![Package](/Assignment%2001//assets/1.png)

- @GetMapping // get all contacts

  - ![Get All](/Assignment%2001//assets/2.png)

- @GetMapping(value = "/{id}") // get contact detail by id

  - ![Get by id](/Assignment%2001//assets/3.png)

- @PostMapping // create new contact

  - ![post data](/Assignment%2001//assets/4.png)

- @PutMapping(value = "/{id}") // update by id

  - ![put data](/Assignment%2001//assets/5.png)

- @DeleteMapping(value = "/contact/{id}") // delete by id

  - ![delete data](/Assignment%2001//assets/6.png)

## Import Postman Collection

### import process

![Model](/Assignment%2001//assets/col1.png)

### result

![Model](/Assignment%2001//assets/col2.png)
