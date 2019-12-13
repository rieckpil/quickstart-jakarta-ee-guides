# Project de.rieckpil.quickstarts/jdbc-data-source-configuration-open-liberty

Steps to run this project:

1. Start your Docker daemon
2. Start a PostgreSQL database with `docker run -e POSTGRES_USER=duke -e POSTGRES_PASSWORD=foobar -p 5432:5432 postgres`
3. Start the application with `mvn liberty:run`