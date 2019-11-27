# Sneaker Shop

## database setup

Use `Docker` to setup local environment

        docker pull postgres
        docker run --name dealerDB -e POSTGRES_DB=car_dev -e 
        POSTGRES_USER=admin -e POSTGRES_PASSWORD=password -p 
        5432:5432 -d postgres

