PROJECT_NAME := test-java-micronaut-rest-jpa
MAIN_CLASS := org.example.Application
JAR = $(wildcard ./target/$(PROJECT_NAME)-*.jar)
RUN_ARGS := -noverify -XX:TieredStopAtLevel=1 -Xnoagent -Djava.compiler=NONE
DEBUG_ARGS := $(RUN_ARGS) -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8000

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
	mvn exec:exec -Dexec.args="$(DEBUG_ARGS) -classpath $(JAR) $(MAIN_CLASS)"

.PHONY: clean
clean:
	mvn -q clean
