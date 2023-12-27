FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8080
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "app.jar"]
