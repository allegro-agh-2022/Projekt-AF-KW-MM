FROM gradle:jdk17 as builder
USER root
WORKDIR /builder
ADD . /builder
RUN gradle build --stacktrace

FROM openjdk:17-alpine
WORKDIR /app
EXPOSE 8080
COPY --from=builder /builder/build/libs/auth.jar ./auth.jar
CMD ["java", "-jar", "auth.jar"]