#!/bin/bash

if [ ! -z "${SERVER_KEYSTORE}" ]; then
	JAVA_OPTS="$JAVA_OPTS -Dsecurity.require-ssl=true -Dserver.ssl.enabled=true -Dserver.ssl.key-store=$SERVER_KEYSTORE"
fi

if [ ! -z "${SERVER_KEYSTORE_PASSWORD_FILE}" ]; then
	echo "Using server keystore password specified in $SERVER_KEYSTORE_PASSWORD_FILE"
	SERVER_KEYSTORE_PASSWORD=`cat $SERVER_KEYSTORE_PASSWORD_FILE`
fi

if [ ! -z "${SERVER_KEYSTORE_PASSWORD}" ]; then
	JAVA_OPTS="$JAVA_OPTS -Dserver.ssl.key-store-password=$SERVER_KEYSTORE_PASSWORD"
fi

if [ ! -z "${TRUST_KEYSTORE}" ]; then
	JAVA_OPTS="$JAVA_OPTS -Dclient-auth=need -Dserver.ssl.trust-store=$TRUST_KEYSTORE"
fi

if [ ! -z "${TRUST_KEYSTORE_PASSWORD_FILE}" ]; then
	echo "Using trust keystore password specified in $TRUST_KEYSTORE_PASSWORD_FILE"
	TRUST_KEYSTORE_PASSWORD=`cat $TRUST_KEYSTORE_PASSWORD_FILE`
fi

if [ ! -z "${TRUST_KEYSTORE_PASSWORD}" ]; then
	JAVA_OPTS="$JAVA_OPTS -Dserver.ssl.trust-store-password=$TRUST_KEYSTORE_PASSWORD"
fi

JAVA_OPTS="$JAVA_OPTS -Dspring.profiles.active=docker"

java $JAVA_OPTS -jar /app/audit-microservice.jar
