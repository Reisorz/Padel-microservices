FROM eclipse-temurin:21-jre-alpine
# Install curl
RUN apk add --no-cache curl
ARG JAR_FILE=target/service-gateway-0.0.1.jar
COPY ${JAR_FILE} service-gateway.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "service-gateway.jar"]