# tdd-project

A simple project to use TDD for the first time

# Prerequisites

The system must allow a user to register with ther name and password, but with some validations...

- the user cannot be under 18 and over 60 years old
- their username can have a maximum of 10 characters and a minimum of 3
- their password must have a minimum of 4 and a maximum of 6

# To Init the project

# on the terminal ./register

```bash
mvn install
```

# To create the docker container with the database - ./

```bash
docker-compose up -d --build
```
