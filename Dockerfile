FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-21-jdk -y

WORKDIR /app

COPY . .


RUN apt-get install maven -y
RUN mvn clean install

FROM openjdk:21-jdk-slim 
EXPOSE 8080

COPY --from=build /app/target/gestao_vagas-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]