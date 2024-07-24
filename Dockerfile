# Use a base image with OpenJDK 21
FROM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Spring Boot jar file to the container
COPY target/back-0.0.1-SNAPSHOT.jar /app/back.jar

# Expose the port on which the Spring Boot application will run
EXPOSE 10000

# Set the command to run the Spring Boot application
CMD ["java", "-jar", "/app/back.jar"]
