# Etapa de compilación
FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /app

COPY . .

RUN mvn clean package


FROM openjdk:17-jdk-slim

EXPOSE 80


COPY --from=build target/app.jar /app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
