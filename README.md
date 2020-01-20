# micronaut-bean-introspection
Micronaut HTTP service (graalvm native, aws lambda) that retrieve simple data from http://api.kanye.rest/


## Requirement

jdk 1.8

micronaut 1.2.8

graalvm 19.2.1


## Installation
mvn clean install -f core-lib/pom.xml

mvn clean package -f micronaut-htt-service/pom.xml 


## Run

cd micronaut-http-service

./sam-local.sh
