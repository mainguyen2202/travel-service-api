# Use an official OpenJDK runtime as a parent image
FROM --platform=amd64 openjdk:17.0.2-slim-buster

LABEL authors="mainguyen"

# Set the working directory in the container
WORKDIR /app

# Install wget
RUN apt-get update && apt-get install -y wget

# Create .mvn/wrapper/ directory
RUN mkdir -p .mvn/wrapper/

# Download Maven Wrapper files
RUN wget https://github.com/apache/maven/raw/master/maven-wrapper.properties -O .mvn/wrapper/maven-wrapper.properties
RUN wget https://github.com/apache/maven/raw/master/maven-wrapper.jar -O .mvn/wrapper/maven-wrapper.jar

COPY mvnw .
RUN chmod +x mvnw

# Copy the project files
# COPY . .
COPY pom.xml .
COPY src src

# Build the application
RUN ./mvnw clean install

# Expose the port your application will run on
EXPOSE 8080

# Specify the command to run your application when the container starts
ENTRYPOINT ["java", "-jar", "target/*.jar"]