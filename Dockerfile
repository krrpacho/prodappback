# Stage 1: Build the application
FROM openjdk:21-jdk AS build
RUN apt-get update && apt-get install -y maven
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Debug step to verify the contents of the /app/target directory
RUN ls -la /app/target

# Stage 2: Create the final image
FROM openjdk:21-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
# Debug step to verify the JAR file is copied correctly
RUN ls -la /app
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
