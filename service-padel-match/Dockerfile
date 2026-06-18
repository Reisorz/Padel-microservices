FROM eclipse-temurin:21-jre-alpine
# Install curl
RUN apk add --no-cache curl
ARG JAR_FILE=target/service-padel-match-0.0.1.jar
COPY ${JAR_FILE} service-padel-match.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "service-padel-match.jar"]