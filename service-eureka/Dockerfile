FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/service-eureka-0.0.1.jar
COPY ${JAR_FILE} service-eureka.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "service-eureka.jar"]