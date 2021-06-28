FROM openjdk:11-jre-slim
VOLUME /temp/feedme
ADD . /temp
COPY target/feedMe-0.0.1-SNAPSHOT.jar feedMe-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/feedMe-0.0.1-SNAPSHOT.jar"]