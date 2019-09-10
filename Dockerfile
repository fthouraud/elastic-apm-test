FROM adoptopenjdk/openjdk11:alpine

ARG ELASTIC_AGENT_VERSION=1.9.0
ARG ELASTIC_BEAT_VERSION=7.3.1

ARG METRICBEAT_PACKAGE=metricbeat-${ELASTIC_BEAT_VERSION}-linux-x86_64
ARG PACKETBEAT_PACKAGE=packetbeat-${ELASTIC_BEAT_VERSION}-linux-x86_64

RUN apk --no-cache add wget &&\
    wget https://search.maven.org/remotecontent?filepath=co/elastic/apm/elastic-apm-agent/$ELASTIC_AGENT_VERSION/elastic-apm-agent-$ELASTIC_AGENT_VERSION.jar -O elastic-apm-agent.jar

RUN wget https://artifacts.elastic.co/downloads/beats/metricbeat/metricbeat-${ELASTIC_BEAT_VERSION}-linux-x86_64.tar.gz &&\
    tar xzvf ${METRICBEAT_PACKAGE}.tar.gz ${METRICBEAT_PACKAGE}/metricbeat --strip-components 1 &&\
    rm ${METRICBEAT_PACKAGE}.tar.gz

RUN wget https://artifacts.elastic.co/downloads/beats/packetbeat/packetbeat-${ELASTIC_BEAT_VERSION}-linux-x86_64.tar.gz &&\
    tar xzvf ${PACKETBEAT_PACKAGE}.tar.gz ${PACKETBEAT_PACKAGE}/packetbeat --strip-components 1 &&\
    rm ${PACKETBEAT_PACKAGE}.tar.gz

RUN apk del wget

COPY docker/metricbeat.yml metricbeat.yml
COPY docker/packetbeat.yml packetbeat.yml

COPY build/libs/sample-app-*.jar application.jar
COPY docker/entrypoint.sh entrypoint.sh
RUN chmod +x ./entrypoint.sh

EXPOSE 8080

ENTRYPOINT ["./entrypoint.sh"]
