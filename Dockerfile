FROM openjdk:8

ENV SERVER_KEYSTORE ""
ENV SERVER_KEYSTORE_PASSWORD ""
ENV SERVER_KEYSTORE_PASSWORD_FILE ""
ENV SERVER_PASSWORD ""
ENV SERVER_PASSWORD_FILE ""
ENV TRUST_KEYSTORE ""
ENV TRUST_KEYSTORE_PASSWORD ""
ENV TRUST_KEYSTORE_PASSWORD_FILE ""

#Add our application to the image
ADD build/libs/audit-microservice-*.jar /app/audit-microservice.jar
ADD docker-entrypoint.sh /app/docker-entrypoint.sh

#Setup a Healthcheck
HEALTHCHECK --interval=30s --timeout=10s CMD curl --fail http://localhost:8080/audit-service/health || exit 1

ENTRYPOINT ["/app/docker-entrypoint.sh"]
