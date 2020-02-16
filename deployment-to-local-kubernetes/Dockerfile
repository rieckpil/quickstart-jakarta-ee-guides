FROM openliberty/open-liberty:19.0.0.12-kernel-java11-openj9-ubi

COPY --chown=1001:0 src/main/liberty/config/server.xml /config
COPY --chown=1001:0 target/deployment-to-local-kubernetes.war /config/dropins/

RUN configure.sh
