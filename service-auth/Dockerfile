FROM eclipse-temurin:21-jre-alpine
# Install curl
RUN apk add --no-cache curl
ARG JAR_FILE=target/service-auth-0.0.1.jar
COPY ${JAR_FILE} service-auth.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "service-auth.jar"]