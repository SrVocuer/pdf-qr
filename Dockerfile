FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

COPY pom.xml .
COPY src ./src

RUN mvn clean package

# Etapa de ejecución
FROM openjdk:17-jdk-slim

EXPOSE 8090

WORKDIR /app

# Copia el archivo JAR desde la etapa de compilación
COPY --from=build /build/target/app.jar 

ENTRYPOINT ["java", "-jar", "app.jar"]
