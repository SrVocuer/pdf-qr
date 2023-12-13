# Etapa de compilación
FROM maven:3.8.4-openjdk-19 AS build

WORKDIR /app

COPY . .

RUN mvn clean package


FROM openjdk:19-jdk-oracle

EXPOSE 8080

ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]