# Use the official Maven image to create a build artifact.
# As a build step
FROM maven:3.8.5-openjdk-22-slim AS build
WORKDIR /home/app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Use the official OpenJDK image to run the application
FROM openjdk:22-jdk-slim
COPY --from=build /home/app/target/back-0.0.1-SNAPSHOT.jar /usr/local/lib/back.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/back.jar"]
