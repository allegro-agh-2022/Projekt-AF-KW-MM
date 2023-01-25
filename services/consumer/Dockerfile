FROM openjdk:17-alpine
WORKDIR /app
EXPOSE 8001
COPY build/libs/consumer.jar ./app.jar
CMD ["java", "-jar", "app.jar"]
