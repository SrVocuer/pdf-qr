# Etapa de compilación
FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package

# Etapa de ejecución
FROM openjdk:17-jdk-slim

EXPOSE 8090

WORKDIR /

# Copiamos el archivo JAR de la etapa de compilación
COPY --from=build target/app.jar .

ENTRYPOINT ["java", "-jar", "app.jar"]