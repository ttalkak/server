FROM openjdk:17-ea-11-jdk-slim
RUN apt-get update && apt-get install -y procps

COPY . .

RUN sh gradlew build

EXPOSE 8000

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "build/libs/gateway-service-0.0.1.jar"]