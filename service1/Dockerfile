FROM openjdk:17-alpine
WORKDIR /app
EXPOSE 8080
COPY service1/build/libs/service1.jar ./app.jar
CMD ["java", "-jar", "app.jar"]