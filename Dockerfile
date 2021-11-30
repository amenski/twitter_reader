FROM adoptopenjdk:11-jre-hotspot
MAINTAINER amenski
WORKDIR /opt/application
ARG JAR_FILE=target/bieber-tweets.jar
COPY ${JAR_FILE} application.jar


ENTRYPOINT ["java", "-jar", "application.jar"]