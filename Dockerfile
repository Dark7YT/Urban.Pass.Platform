FROM openjdk:17
ADD target/urbanpassplatform-docker-compose.jar urbanpassplatform-docker-compose.jar
ENTRYPOINT ["java", "-jar", "urbanpassplatform-docker-compose.jar"]