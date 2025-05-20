FROM openjdk:21-jdk-slim
# Install curl
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*
RUN mkdir -p /app/uploads
ENV FILE_UPLOAD_DIR=/app/uploads

ARG JAR_FILE=target/service-user-0.0.1.jar
COPY ${JAR_FILE} service-user.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "service-user.jar"]