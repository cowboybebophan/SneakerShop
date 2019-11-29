# Sneaker Shop

![Flight Club NYC store.](https://cdn.vox-cdn.com/thumbor/dF0zZT8QnwLnGj4APGe1wTDU2hI=/0x0:6720x4480/1820x1213/filters:focal(2823x1703:3897x2777):format(webp)/cdn.vox-cdn.com/uploads/chorus_image/image/58617409/PHOTO_CREDIT__Giulia_White__2F_GOAT.0.jpg)

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.  
See deployment for notes on how to deploy the project on a live system.

## Database setup

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