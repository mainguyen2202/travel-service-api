

FROM openjdk:21-jdk

COPY  target/*.jar travel-service-api.jar

ENTRYPOINT ["java", "-jar", "/travel-service-api.jar"]














