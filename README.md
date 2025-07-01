# Facebook Webhook Server

This project implements a server for handling webhook events from Facebook/Instagram and integrates with Kafka for message processing and delivery.

## Features
- Receiving and processing webhook requests from Facebook/Instagram
- Kafka integration for message queues
- Helper services for Instagram operations

## Requirements
- Java 17+
- Gradle
- Docker (for containerization)
- Docker Compose (for running dependencies and the app)

## Build

```bash
./gradlew build
```

The built jar file will be located in `build/libs/`.

## Run

### Locally with Gradle

```bash
./gradlew bootRun
```

### With Docker

Build the Docker image:

```bash
docker build -t facebook-webhook-server .
```

Run the container:

```bash
docker run -p 8080:8080 facebook-webhook-server
```

### With Docker Compose

The project includes a `docker-compose.yml` file to run the app with dependencies (e.g., Kafka):

```bash
docker-compose up --build
```

## Configuration

Main configuration parameters are set in `src/main/resources/application.yml`.

## Tests

To run tests:

```bash
./gradlew test
```
