# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the application jar file to the container
ARG JAR_FILE=target/back-app-1.0.0.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
