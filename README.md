[![Build Status](https://travis-ci.org/campisano/test_java_micronaut_rest_jpa.svg?branch=master "Build Status")](https://travis-ci.org/campisano/test_java_micronaut_rest_jpa)
[![Test Coverage](https://codecov.io/gh/campisano/test_java_micronaut_rest_jpa/branch/master/graph/badge.svg "Test Coverage")](https://codecov.io/gh/campisano/test_java_micronaut_rest_jpa)
[![Code Quality](https://img.shields.io/lgtm/grade/java/g/campisano/test_java_micronaut_rest_jpa.svg "Code Quality")](https://lgtm.com/projects/g/campisano/test_java_micronaut_rest_jpa/context:java)
[![Docker Hub](https://img.shields.io/docker/image-size/riccardocampisano/public/test_java_micronaut_rest_jpa-latest?label=test_java_micronaut_rest_jpa-latest&logo=docker)](https://hub.docker.com/r/riccardocampisano/public/tags?name=test_java_micronaut_rest_jpa)

# Test Java Micronaut Rest JPA with CI and Coverage

mn create-app org.example.test-java-micronaut-rest-jpa --inplace --lang=java --build=maven --profile=service --features=application,data-hibernate-jpa,http-client,http-server,logback
