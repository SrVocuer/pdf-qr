# Etapa de compilación
FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /app

COPY . .

RUN mvn clean package


FROM openjdk:17-jdk-slim

EXPOSE 80


ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","${JAVA_OPTS}","-jar","/app.jar"]