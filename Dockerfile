FROM openjdk:18.0.2.1-jdk-bullseye
MAINTAINER baeldung.com
COPY target/spring-boot-example-0.0.1-SNAPSHOT.jar application.jar
ENTRYPOINT ["java","-jar","/application.jar"]
