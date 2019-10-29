# My Pet Shop Shop API
## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Decisions Made](#decisions-made)
* [API](#api)
* [Github repository](#github-repository)
* [Setup](#setup)

## General info
I created this api to demonstrate my abilities as software developer with a simple crud.

## Technologies

* Spring Boot (Actuator, Security, JPA)
* H2 (In memory)
* JUnit
* Flyway
* Jackson Mapper
* Basic Authentication
* Docker
* Swagger2

## Decisions Made:
* Security: Basic Authentication on the data in transit in order to ensure a reliable communication to the single test application. Think about the use of oAuth2 instead of basic authentication for 
solution which requires a high level of security.  
* Spring boot: Once I have a large background with java Springboot was the natural choose. With Spring is possible to create robust apis in few lines of code.
* H2: The best solution to integrate natively with spring-data.
* Flyway: To create the database as a code using the migrations 
* JUNIT: All of the unit tests were created using junit and Mockito. Because of few time to conclude the task I created the Unit tests only to the service layer.
* One single project: Once this is a small test to evaluate my skills I have invlude everything in one single project. It is recommended to split on small project in order to be possible increase the individual scalability. 
* Swagger2: One of the most used framework to document apis.
* Lack Integration tests: Once it is possible to test the api through Swagger Integration tests weren't created.
* Exception handling: Use standard (SQLException, JDBCException...) exceptions instead of custom. 
* Logging: Rolling appender should be considered to rotate the log file to avoid large files on the storage.


## API
### Product

```
* GET - product/: List all available products 
* GET - product/{id}: Get information about a product based on the product ID.
* POST - product: Insert a new product.
* PUT - product: Update product data.
* DELETE - product/id: Remove a product.
```
### User
```
* GET - user/: List all available Users 
* GET - user/{id}: Get information about a user based on the product ID.
* POST - user: Insert a new user.
* PUT - user: Update user data.
* DELETE - user/{id}: Remove a user.
```
### Cart
```
*  
```

## Github repository

```
https://github.com/abdesousa/mypetshop-api.git
```


## Setup
Once the docker image isn't deployed on the DockerHUB, you should follow the steps below:

1- Clone the project.

```
https://github.com/abdesousa/mypetshop-api.git
```
2- Build the project. 

```
mvn clean package docker:build
```

3- Run the container.

```
docker-compose up
```

4- Open the browser and type http://localhost:8090/swagger-ui.html.

```
User: admin
Password: admin
```



