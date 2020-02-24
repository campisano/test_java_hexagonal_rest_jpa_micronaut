MAIN := org.example.Application
JAR := ./target/test-java-micronaut-rest-jpa-0.1-shaded.jar
DEBUG := -Dexec.args="-noverify -XX:TieredStopAtLevel=1 -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8000 -Xnoagent -Djava.compiler=NONE -classpath $(JAR) $(MAIN)"

.PHONY: test
test:
	mvn test

.PHONY: run
run:
	mvn compile exec:exec

.PHONY: debug
debug:
	mvn package exec:exec $(DEBUG)

.PHONY: clean
clean:
	mvn clean
