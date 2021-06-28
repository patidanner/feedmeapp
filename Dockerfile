FROM openjdk:11-jre-slim
VOLUME /temp/feedme
COPY target/feedMe-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]