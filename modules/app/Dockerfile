FROM adoptopenjdk/openjdk8-openj9:latest
VOLUME /tmp
COPY target/*.jar security-authorization-app.jar
ENTRYPOINT ["java", "-Xshareclasses:name=kerubin", "-jar", "/security-authorization-app.jar"]