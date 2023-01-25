FROM openjdk:17-alpine
WORKDIR /app
EXPOSE 8003
COPY build/libs/products.jar ./app.jar
CMD ["java", "-jar", "app.jar"]
