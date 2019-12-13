FROM open-liberty:kernel-java11

ENV POSTGRES_PASSWORD duke
ENV POSTGRES_USER foobar

COPY --chown=1001:0  src/main/liberty/config/postgresql-42.2.8.jar /config/
COPY --chown=1001:0  src/main/resources/liberty/config/server.xml /config
COPY --chown=1001:0  target/jdbc-data-source-configuration-open-liberty.war /config/dropins/
