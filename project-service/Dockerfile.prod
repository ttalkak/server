FROM openjdk:17-ea-11-jdk-slim
RUN apt-get update && apt-get install -y procps

COPY . .

RUN sh gradlew build

WORKDIR /app/build/libs

ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "project-0.0.1.jar"]