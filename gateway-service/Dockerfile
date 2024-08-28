FROM openjdk:17-ea-11-jdk-slim
RUN apt-get update && apt-get install -y procps

COPY . .

RUN sh gradlew build

ENTRYPOINT ["java", "-jar", "build/libs/gateway-0.0.1.jar"]