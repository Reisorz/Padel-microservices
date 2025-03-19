FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/service-padel-court-0.0.1.jar
COPY ${JAR_FILE} service-padel-court.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "service-padel-court.jar"]