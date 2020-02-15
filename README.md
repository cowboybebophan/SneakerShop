# Sneaker Shop :athletic_shoe: 

A back-end web development project.

## Table of contents
* [Sneaker Shop](#sneaker-shop-athletic_shoe)     
  * [Idea](#idea)     
  * [Tech Stack](#tech-stack)       
* [Environment Setup](#environment-setup)    
  * [Maven](#maven)    
  * [Docker](#docker)  
  * [Postgres](#postgres)   
  * [Redis](#redis)
  * [Database Connection](#database-connection)  
  * [Database Migration](#database-migration)     
* [CI/CD](#cicd)
  * [GitHub](#github)
  * [Unit Test](#unit-test)
  * [Packaging](#packaging)
  * [Build Docker Image](#build-docker-image)
  * [Containerized App](#run-containerized-app)

## Idea
Imagine walking into a sneaker shop and seeing hundreds of sneakers displaying in front of you.     

There are so many factors you need to consider: `size` `style` `brand` `color` `material`...   
     
How do you choose the perfect pair?         

**SneakerShop** is a system that takes in all the factors into account and chooses the perfect sneaker for you.

## Tech Stack

**Database** : `PostgreSQL`  
**Testing** : `JUnit` `Mockito`  
**Frameworks** : `Spring-boot` `Hibernate`   
**Cloud Deployment** : `AWS S3` `AWS SQS`   
**Tools** : `Git` `Docker` `Maven` `Flyway` `pgAdmin` `Postman` `Redis`

# Environment Setup
These instructions will help you setup the environment that the project is based on.    

## Maven    
>Use `Maven` to manage the project.     
>
`Maven` is a software project management and comprehension tool.   
 
It manages a project's **dependencies** and helps with **build automation**.

All the dependencies and plugins are in the `pom.xml` file.     

## Docker
>Use `Docker` to create containers.
>
List all Docker images and containers:

    docker images
    docker ps -a

Pull images: 
    
    docker pull <image_name>
    
Restart an exited container:
    
    docker start <container_name>

## Postgres
>Use `Postgres` as database.
>
Pull image from postgres:

    docker pull postgres

Create container: 
    
    docker run --name <container_name> -e POSTGRES_DB=<server_name> 
    -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres
    
Note: If port 5432 is already in use, kill all postgres processes by using:
    
    sudo pkill -u postgres

## Redis
>Use `Redis` to achieve caching for better controller performance.
>
Pull image from redis:

    docker pull redis
    
Create container: 

    docker run -p 6379:6379 --name spring-redis -d redis
    
Enter interactive terminal:

    docker exec -it spring-redis /bin/bash 
    
Redis commandline:

    redis-cli keys *
    redis-cli FLUSHDB
    redis-cli info stats | grep 'keyspace_*'

## Database Connection
>Use `VM Option` to create database connection.
>
    -Ddatabase.driver=org.postgresql.Driver
    -Ddatabase.url=jdbc:postgresql://${database_host}:${port}/${database_name}
    -Ddatabase.user=${user_name}
    -Ddatabase.password=${password}
    -Ddatabase.dialect=org.hibernate.dialect.PostgreSQL9Dialect

## Database Migration
>Use `Flyway` as migration tool.
>

`Flyway` is a `Maven` plugin, it makes database migrations easy.     

Compile your source first:  

    mvn compile
    
Database migration: 

    mvn flyway:migrate
    
# CI/CD

You should have completed the following stages before you work with DevOps engineer.

  * Upload source code to GitHub repository
  * Fulfill unit test stage in docker container
  * Package **.war** file in docker container
  * Build Docker image with **.war** file and Dockerfile
  * Launch containerized application successfully

## GitHub

Make sure you have uploaded the latest version of your source code in a public GitHub repository.   

**Don't include any credential or database information in your source code.**

## Unit Test
>Use `Docker` to pull `Maven` image and run an interactive container.
>
    docker pull maven:3.6.0-jdk-8
    docker run -it maven:3.6.0-jdk-8 /bin/bash

>Use `Git` to retrieve source code from `GitHub`.
>
    git clone <repository_url>
    
>Switch to the project folder, use `Flyway` to migrate data.
>
Notice: we are working in a maven container so the database connection is no longer through localhost:5432.
The database_host should be the internal IP address for the `postgreSQL` server container. 

    mvn clean compile flyway:migrate -Ddatabase.url=jdbc:postgresql://${database_host}:5432/${database_name} 
    -Ddatabase.user=${user_name} -Ddatabase.password=${password}
    
>Run unit tests in the container.
>
    mvn test -Ddatabase.url=jdbc:postgresql://${database_host}:5432/${database_name} -Ddatabase.user=${user_name} 
    -Ddatabase.password=${password} -Daws.accessKeyId=${access_key} -Daws.secretKey=${secret_key} 
    -Ddatabase.dialect=org.hibernate.dialect.PostgreSQL9Dialect -Ddatabase.driver=org.postgresql.Driver
    
## Packaging
>Use `Maven` to package a .war file.
>
    mvn compile package -DskipTests=true -q
    
## Build Docker Image
Build your image with Dockerfile (including environment variables like DB_URL, DB_PASSWORD), 
setenv.sh (use environment variables as JVM options) and .war file.

## Run Containerized App
>Launch a containerized application in your local machine.
>

    docker run -e ENV_VAR=VAL ${IAMGE_ID}
    
 
    








