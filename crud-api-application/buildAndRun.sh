#!/bin/sh
mvn clean package && docker build -t de.rieckpil.quickstarts/crud-api-application .
docker rm -f crud-api-application || true && docker run -d -p 9080:9080 -p 9443:9443 --name crud-api-application de.rieckpil.quickstarts/crud-api-application