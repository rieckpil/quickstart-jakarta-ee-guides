@echo off
call mvn clean package
call docker build -t de.rieckpil.quickstarts/deployment-to-local-kubernetes .
call docker tag de.rieckpil.quickstarts/deployment-to-local-kubernetes localhost:5000/jakarta-ee-app
call docker push localhost:5000/jakarta-ee-app
