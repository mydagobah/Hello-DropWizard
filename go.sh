#!/bin/sh
mvn clean package

java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=15988 -jar target/my-app-1.0-SNAPSHOT.jar server config/hello-world.yml
