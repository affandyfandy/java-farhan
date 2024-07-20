# Follow the example

## `pre-requirement`

- JDK : 18.0.2.1
  - ![jdk version](assets/jdk.png)
- Database : mysql (latest version)
  - ![mysql version](assets/mysql.png)
- IDE : intelij

## Create Spring Project

![Init project](assets/initproject.png) with springinitializr

## Maven Dependencies

![Dependencies](assets/dependencies.png)

## Create Database

### Create Database and Table

![Init database](assets/db2.png)

### Result

![Init database](assets/db1.png)

## Configure Properties

![Configure properties](assets/properties.png)

## Create Model

![Model](assets/model.png)

## Create Repository

![Repository](assets/repo.png)

## Create Controller

- Package

  - ![Package](assets/1.png)

- @GetMapping // get all contacts

  - ![Get All](assets/2.png)

- @GetMapping(value = "/{id}") // get contact detail by id

  - ![Get by id](assets/3.png)

- @PostMapping // create new contact

  - ![post data](assets/4.png)

- @PutMapping(value = "/{id}") // update by id

  - ![put data](assets/5.png)

- @DeleteMapping(value = "/contact/{id}") // delete by id

  - ![delete data](assets/6.png)

## Import Postman Collection

### import process

![Model](assets/col1.png)

### result

![Model](assets/col2.png)
