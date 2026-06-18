FROM eclipse-temurin:21-jre-alpine
# Install curl
RUN apk add --no-cache curl
ARG JAR_FILE=target/service-match-user-0.0.1.jar
COPY ${JAR_FILE} service-match-user.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "service-match-user.jar"]