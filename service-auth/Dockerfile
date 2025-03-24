FROM openjdk:21-jdk-slim
# Install curl
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*
ARG JAR_FILE=target/service-auth-0.0.1.jar
COPY ${JAR_FILE} service-auth.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "service-auth.jar"]