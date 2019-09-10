#!/bin/sh

JAVA_OPS="${JAVA_OPS} -javaagent:elastic-apm-agent.jar"
JAVA_OPS="${JAVA_OPS} -Delastic.apm.service_name=sample-app-${INFO_ENVIRONMENT}-${INFO_CLASSIFIER}"
JAVA_OPS="${JAVA_OPS} -Delastic.apm.server_urls=${APM_SERVER_HOST}"
JAVA_OPS="${JAVA_OPS} -Delastic.apm.secret_token="
JAVA_OPS="${JAVA_OPS} -Delastic.apm.enable_log_correlation=true"
JAVA_OPS="${JAVA_OPS} -Delastic.apm.application_packages=com.ekino.fth"

echo "Launching metricbeat..."
./metricbeat --strict.perms=false -E output.elasticsearch.hosts=["${ELASTICSEARCH_HOST}"] &

echo "Launching application..."
java ${JAVA_OPS} -jar application.jar
