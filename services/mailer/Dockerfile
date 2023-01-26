FROM openjdk:17-alpine
WORKDIR /app
EXPOSE 8005
COPY build/libs/mailer.jar ./app.jar
CMD ["java", "-jar", "app.jar"]
