@echo off
call mvn clean package
call docker build -t de.rieckpil.quickstarts/crud-api-application .
call docker rm -f crud-api-application
call docker run -d -p 9080:9080 -p 9443:9443 --name crud-api-application de.rieckpil.quickstarts/crud-api-application