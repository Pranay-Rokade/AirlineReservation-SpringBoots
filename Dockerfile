FROM openjdk:21
ARG JAR_FILE=target/*.jar
COPY target/assignment1-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8049
