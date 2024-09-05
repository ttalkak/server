FROM openjdk:17-ea-11-jdk-slim
RUN apt-get update && apt-get install -y procps

COPY . .

RUN sh gradlew build
RUN chmod -R 777 build/libs/project-0.0.1.jar


ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "build/libs/project-0.0.1.jar"]