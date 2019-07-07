FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar security-authorization-app.jar
ENTRYPOINT ["java","-jar","/security-authorization-app.jar"]