FROM eclipse-temurin:21-jre-alpine
# Install curl
RUN apk add --no-cache curl
ARG JAR_FILE=target/service-padel-court-0.0.1.jar
COPY ${JAR_FILE} service-padel-court.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "service-padel-court.jar"]