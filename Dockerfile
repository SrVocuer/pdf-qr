# Etapa de compilación
FROM maven:3.8.4-openjdk-17 AS build

COPY ..

RUN .mvn clean package

# Etapa de ejecución
FROM openjdk:17-jdk-slim

EXPOSE 8090

WORKDIR /app

# Copia el archivo JAR desde la etapa de compilación
COPY --from=build /build/target/app.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
