FROM eclipse-temurin:21-jdk-jammy
# Install curl
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*
ARG JAR_FILE=target/service-notification-0.0.1.jar
COPY ${JAR_FILE} service-notification.jar
EXPOSE 8087
ENTRYPOINT ["java", "-jar", "service-notification.jar"]