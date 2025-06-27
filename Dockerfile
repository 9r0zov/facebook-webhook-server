FROM gradle:8.7-jdk21 AS build
WORKDIR /app
COPY . .
RUN gradle bootJar --no-daemon

FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/build/libs/facebook-webhook-server-0.0.1.jar app.jar
EXPOSE 8000

ENTRYPOINT ["java", "-jar", "app.jar"]
