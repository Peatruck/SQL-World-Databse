# World Database Access

This repository provides a variety of methods for accessing predefined summaries
of the World database defined by the world.sql script (available: https://dev.mysql.com/doc/world-setup/en/world-setup-installation.html).

## Requirements

- Java 21
- Database software (the linked schema assumes MySQL)

## Setup

1. Fork the repository
2. Clone the repository from github:`git clone <your-repository-url`
3. Ensure that the world database is set up on your computer by running the world.sql script
4. Add application.properties to the resources folder and add the following, replacing your values with placeholders:
```properties
spring.datasource.url=<your-database-url>
spring.datasource.username=<your-database-username>
spring.datasource.password=<your-database-password>

spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
```
If using a local MySQL database on the default port, the database URL should be `jdbc:mysql://localhost:3306/world`.

Summary methods are defined in the `WorldService` class while basic CRUD operations are defined on the repository classes.