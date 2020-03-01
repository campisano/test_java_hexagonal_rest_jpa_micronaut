PROJECT_NAME := test-java-micronaut-rest-jpa
MAIN_CLASS := org.example.Application
JAR = $(wildcard ./target/$(PROJECT_NAME)-*.jar)
DEBUG = -Dexec.args="-noverify -XX:TieredStopAtLevel=1 -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8000 -Xnoagent -Djava.compiler=NONE -classpath $(JAR) $(MAIN_CLASS)"

.PHONY: test
test: clean
	mvn test

.PHONY: run
run: clean
	mvn compile exec:exec

.PHONY: package
package: clean
	mvn -Dmaven.test.skip=true package

.PHONY: debug
debug: package
	mvn exec:exec $(DEBUG)

.PHONY: clean
clean:
	mvn -q clean
