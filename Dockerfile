# Use an official OpenJDK 22 runtime as a parent image for the build stage
FROM openjdk:22-jdk-slim AS build
WORKDIR /app

# Install Maven
RUN apt-get update && apt-get install -y maven

# Copy the current directory contents into the container at /app
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Use the same OpenJDK 22 runtime as a parent image for the runtime stage
FROM openjdk:22-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/back-0.0.1-SNAPSHOT.jar app.jar

# Add metadata to the image to describe which port the container is listening on at runtime.
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
