FROM openjdk:21-jdk-slim
# Install curl
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*
ARG JAR_FILE=target/service-padel-match-0.0.1.jar
COPY ${JAR_FILE} service-padel-match.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "service-padel-match.jar"]