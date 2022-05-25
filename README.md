[![Build Status](https://gitlab.com/campisano/test_java_micronaut_rest_jpa/badges/master/pipeline.svg "Build Status")](https://gitlab.com/campisano/test_java_micronaut_rest_jpa/-/pipelines)
[![Sonar Coverage](https://sonarcloud.io/api/project_badges/measure?project=test_java_micronaut_rest_jpa&metric=coverage "Sonar Coverage")](https://sonarcloud.io/dashboard?id=test_java_micronaut_rest_jpa)
[![Sonar Maintainability](https://sonarcloud.io/api/project_badges/measure?project=test_java_micronaut_rest_jpa&metric=sqale_rating "Sonar Maintainability")](https://sonarcloud.io/dashboard?id=test_java_micronaut_rest_jpa)
[![Docker Image](https://img.shields.io/docker/image-size/riccardocampisano/public/test_java_micronaut_rest_jpa-latest?label=test_java_micronaut_rest_jpa-latest&logo=docker "Docker Image")](https://hub.docker.com/r/riccardocampisano/public/tags?name=test_java_micronaut_rest_jpa)

# Test Java Micronaut Rest JPA with CI and Coverage

mn create-app org.example.test-java-micronaut-rest-jpa --inplace --lang=java --build=maven --profile=service --features=application,data-hibernate-jpa,http-client,http-server,logback
