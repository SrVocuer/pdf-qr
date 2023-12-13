# Etapa de compilación
FROM maven:3.8.4-openjdk-19 AS build

RUN mvn clean package
FROM openjdk:19-jdk-oracle

EXPOSE 8080

VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]