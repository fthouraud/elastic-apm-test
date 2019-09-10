# elastic-apm-test
A dumb project to test the whole Elastic stack including APM solution using Docker.

# Sample application

## Java

The project is written and compiled with Java 11.

## Build

It uses a Gradle wrapper to build the app.

To build the project, simply run:
```sh
./gradlew build
```

## Endpoints:

It describes the base pathes of the application.

### Get all foos

`/foos`

### Get a single foo

`/foos/{a-foo-id}`

### Get all users

`/users`

This endpoint is secured and require an authorization header. The user is `user` and its password is `ekino`.

You can add the header `Authorization` with value `Basic dXNlcjpla2lubw==`.

# The Elastic stack

## Docker

The whole stack is based on Docker and it uses the `docker-compose.yml` to manage it.

**N.B. The Java application must have been built before because its JAR is included in a Docker image.**

To build and deploy the whole stack, run:
```sh
docker-compose -f docker-compose.yml up -d
```

## Accessing the components

The interesting part here is the Kibana application to view the APM metrics and logs.

It is accessible here:  http://localhost:5601/app/kibana

The other components:
APM server: http://localhost:8200
Elasticsearch: http://localhost:9200
Logstash: http://localhost:9600

# Adding a bit of volumetry

In order to have a bit of date without spamming the endpoints on my own ; I used JMeter.

The profile I used is `jmeter-profile.jmx`.

You can find more information on JMeter [here](https://jmeter.apache.org/).
