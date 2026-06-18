FROM eclipse-temurin:21-jre-alpine
# Install curl
RUN apk add --no-cache curl
ARG JAR_FILE=target/service-notification-0.0.1.jar
COPY ${JAR_FILE} service-notification.jar
EXPOSE 8087
ENTRYPOINT ["java", "-jar", "service-notification.jar"]