# Sneaker Shop

## Table of Contents
* [Sneaker Shop](#sneaker-shop)     
  * [Description](#description)     
  * [Tech Stack](#tech-stack)       
* [Environment Setup](#environment-setup)        
  * [Local Environment Setup](#local-environment-setup)     
  * [Database Setup](#database-setup)       

## Description
Imagine walking into a sneaker shop and seeing hundreds of sneakers displaying in front of you.

How do you choose the perfect pair? There are so many factors you need to consider: size, style, brand, color, material...

**SneakerShop** is a system that takes in all the factors into account and chooses the perfect sneaker for you.

<img src="https://cdn.vox-cdn.com/thumbor/dF0zZT8QnwLnGj4APGe1wTDU2hI=/0x0:6720x4480/1820x1213/filters:focal(2823x1703:3897x2777):format(webp)/cdn.vox-cdn.com/uploads/chorus_image/image/58617409/PHOTO_CREDIT__Giulia_White__2F_GOAT.0.jpg" width="74%">

## Tech Stack
These are the technologies I have been using while building up the project.

***Tools*** : `Git` `Docker` `Maven` `Flyway` `pgAdmin` `Postman`  
***Frameworks*** : `Spring-boot` `Hibernate`       
***Testing*** : `Junit` `Mockito`  
***Database*** : `PostgresSQL`   

# Environment Setup
These instructions will help you setup the environment that the project is based on.    

## Local Environment Setup
>Use `Docker` to setup local environment.
>
List all Docker images and containers:

    docker images
    docker ps -a

Pull image from postgres: 
    
    docker pull postgres
    
Create container: 
    
    docker run --name <container_name> -e POSTGRES_DB=<server_name> 
    -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres
    
Restart a exited container:
    
    docker start <container_name>

Note: If port 5432 is already in use, kill all postgres processes by using:
    
    sudo pkill -u postgres

## Database Setup


