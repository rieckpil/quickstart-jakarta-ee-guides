#!/bin/sh
mvn clean package && docker build -t de.rieckpil.quickstarts/deployment-to-payara-intellij .
docker rm -f deployment-to-payara-intellij || true && docker run -d -p 9080:9080 -p 9443:9443 --name deployment-to-payara-intellij de.rieckpil.quickstarts/deployment-to-payara-intellij