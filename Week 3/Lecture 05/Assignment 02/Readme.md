# Employee Management App

## `pre-requirement`

- JDK : 18.0.2.1
  - ![jdk version](assets/jdk.png)
- Database : mysql (latest version)
  - ![mysql version](assets/mysql.png)
- IDE : intelij

## Postman Result

- @GetMapping // get all employees

  - ![Get All](assets/all.png)

- @GetMapping(value = "/{id}") // get employee detail by id

  - ![Get by id](assets/details.png)

- @PostMapping // create new data

  - ![post data](assets/create.png)

- @PutMapping(value = "/{id}") // update by id

  - ![put data](assets/edit.png)

- @DeleteMapping(value = "/{id}") // delete by id

  - ![delete data](assets/delete.png)

- @GetMapping

  - ![employ dep data](assets/reqdep.png)

- @PostMapping

  - ![csv data](assets/uploadcsv.png)
