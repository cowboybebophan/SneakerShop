# Sneaker Shop

## database setup

Use `Docker` to setup local environment

        docker pull postgres
        docker run --name <container_name> -e POSTGRES_DB=<server_name> -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres

