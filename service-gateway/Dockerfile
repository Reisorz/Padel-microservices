FROM openjdk:21-jdk-slim
# Install curl
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*
ARG JAR_FILE=target/service-gateway-0.0.1.jar
COPY ${JAR_FILE} service-gateway.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "service-gateway.jar"]