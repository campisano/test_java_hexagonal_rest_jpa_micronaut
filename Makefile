NAME := test-java-micronaut-rest-jpa
MAIN := org.example.Application
JAR = $(wildcard ./target/$(NAME)-*.jar)
DEBUG = -Dexec.args="-noverify -XX:TieredStopAtLevel=1 -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8000 -Xnoagent -Djava.compiler=NONE -classpath $(JAR) $(MAIN)"

.PHONY: test
test: clean
	mvn test

.PHONY: run
run: clean
	mvn compile exec:exec

.PHONY: package
package: clean
	mvn package

.PHONY: debug
debug: package
	mvn exec:exec $(DEBUG)

.PHONY: clean
clean:
	@mvn -q clean
