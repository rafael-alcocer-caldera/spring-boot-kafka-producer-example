# SPRING BOOT KAFKA PRODUCER EXAMPLE

## Synopsis

The project is a Spring Boot Application that is a Kafka Producer.

## Motivation

I wanted to do a Kafka Producer with Spring Boot Application.

## PRE REQUIERMENTS

- Kafka Server must be running
- Postman to send messages

## Example to run kafka on windows

<pre><code>

Base directory:
C:\RAC\kafka_2.13-2.4.0\bin\windows

Start Zookeeper:
zookeeper-server-start.bat ../../config/zookeeper.properties

Start Kafka:
kafka-server-start.bat ../../config/server.properties

</code></pre>

## POSTMAN REQUESTS

<pre><code>

POST
http://localhost:8089/kafka/producer

BODY
{
	"id": "1",
	"topicName": "rac-topic",
	"json": {
		"nombre": "RAFAEL",
		"apellido": "ALCOCER",
		"edad": 1000,
		"fecha": "1900 - 08 - 13"
	}
}

RESPONSE

</code></pre>

## License

All work is under Apache 2.0 license