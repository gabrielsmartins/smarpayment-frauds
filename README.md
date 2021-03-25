# Microservice for Frauds using Hexagonal Architecture

[![Build Status](https://travis-ci.com/gabrielsmartins/smartpayment-frauds.svg?branch=master)](https://travis-ci.com/gabrielsmartins/smartpayment-frauds)

This Project is an Example Implementation of a Hexagonal Architecture and Event Collaboration using the following technologies:

- JDK 11
- Gradle
- Spring Boot
- Spring Data MongoDB Reactive  
- Spring Kafka
- Spring Cloud OpenFeign
- Spring Cloud Sleuth
- Jaeger

## Running Application

1.Run Docker Services:

```bash
docker-compose up -d
```

2.Run Application:

```bash
./gradlew bootRun
```

OR

```bash
./gradlew bootRun --args='--spring.profiles.active=<profile>'
```

### Using Tools

1.For Jaeger go to browser and navigate: http://localhost:16686/

2.For Kafka Control Center UI go to browser and navigate: http://localhost:9021/

3.For MongoDB use MongDCompass with URL:

```bash
mongodb://localhost:27017
```

### Other Projects
* [SmartPayment Orders](https://github.com/gabrielsmartins/smartpayment-orders)
* [SmartPayment Payments](https://github.com/gabrielsmartins/smartpayment-payments)
* [SmartPayment Account](https://github.com/gabrielsmartins/smartpayment-account)
* [SmartPayment Credit Card](https://github.com/gabrielsmartins/smartpayment-credit-card)

