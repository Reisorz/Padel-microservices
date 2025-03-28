FROM openjdk:21-jdk-slim
# Install curl
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*
ARG JAR_FILE=target/service-match-user-0.0.1.jar
COPY ${JAR_FILE} service-match-user.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "service-match-user.jar"]