#!/bin/sh
mvn clean package && docker build -t de.rieckpil.quickstarts/jdbc-data-source-configuration-open-liberty .
docker rm -f jdbc-data-source-configuration-open-liberty || true && docker run -d -p 9080:9080 -p 9443:9443 --name jdbc-data-source-configuration-open-liberty de.rieckpil.quickstarts/jdbc-data-source-configuration-open-liberty