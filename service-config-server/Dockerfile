FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/service-config-server-0.0.1.jar
COPY ${JAR_FILE} service-config-server.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "service-config-server.jar"]