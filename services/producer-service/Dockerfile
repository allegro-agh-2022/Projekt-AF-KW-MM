FROM openjdk:17-alpine
WORKDIR /app
EXPOSE 8082
COPY build/libs/producer-service.jar ./app.jar
CMD ["java", "-jar", "app.jar"]
