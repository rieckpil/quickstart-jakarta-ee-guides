@echo off
call mvn clean package
call docker build -t de.rieckpil.quickstarts/deployment-to-payara-intellij .
call docker rm -f deployment-to-payara-intellij
call docker run -d -p 9080:9080 -p 9443:9443 --name deployment-to-payara-intellij de.rieckpil.quickstarts/deployment-to-payara-intellij