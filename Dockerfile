

# FROM openjdk:21


# COPY  target/*.jar travel-app-docker.jar


# ENTRYPOINT ["java", "-jar", "/travel-app-docker.jar"]

# FROM openjdk:21

# WORKDIR /opt
# ENV PORT 8080
# EXPOSE  8080
# COPY target/*.jar /opt/app.jar


# ENTRYPOINT exec java $JAVA_OPTS -jar app.jar


FROM eclipse-temurin:17-jdk-focal
 
WORKDIR /app
 
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
 
COPY src ./src
 
CMD ["./mvnw", "spring-boot:run"]













