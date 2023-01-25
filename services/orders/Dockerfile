FROM openjdk:17-alpine
WORKDIR /app
EXPOSE 8004
COPY build/libs/orders.jar ./app.jar
CMD ["java", "-jar", "app.jar"]
