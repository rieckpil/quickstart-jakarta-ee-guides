#!/bin/sh
mvn clean package && docker build -t de.rieckpil.quickstarts/deployment-to-local-kubernetes .
docker tag de.rieckpil.quickstarts/deployment-to-local-kubernetes localhost:5000/jakarta-ee-app
docker push localhost:5000/jakarta-ee-app
