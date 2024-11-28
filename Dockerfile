FROM openjdk:22-jdk-slim

WORKDIR /app

COPY target/ServerForChess-0.0.1-SNAPSHOT.jar /app/myApp.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","myApp.jar"]
