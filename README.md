# Java Microservice Framework

[![CircleCI](https://circleci.com/gh/lucksolutions/spring-boot-microservice.svg?style=svg)](https://circleci.com/gh/lucksolutions/spring-boot-microservice)

## Setup
1. Build the microservice app
```bash
./gradlew build
```
2. Start the application
```bash
docker-compose up --build
```

## Kafka Integration Testing
1. Add the following entry to your /etc/hosts file
```
127.0.0.1	kafka
```
2. [Download Kafka](http://kafka.apache.org/downloads)
3. Unzip Kafka. The installation directory will be referred to as $KAFKA_HOME
4. Run the following command to create a requset producer
```bash
$KAFKA_HOME/bin/./kafka-console-producer.sh --broker-list localhost:9092 --topic test
```
5. Run the following command to create a response consumer
```bash
$KAFKA_HOME/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic testResponse --from-beginning
```
6. In the producer terminal, type your message. You should then see a response on the consumer console.

## Documentation
* [Spring Boot + Kafka](http://docs.spring.io/spring-boot/docs/1.5.2.RELEASE/reference/htmlsingle/#boot-features-kafka)

## Endpoints

### Public
* API Documentation - [/swagger-ui.html](http://localhost/swagger-ui.html)

### Admin
* Log Configuration - /loggers
* Audit Events - /auditevents