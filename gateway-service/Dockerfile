FROM openjdk:17-ea-11-jdk-slim
RUN apt-get update && apt-get install -y procps

COPY . .

RUN ./gradlew build

RUN ls -al

RUN pwd

ENTRYPOINT ["java", "-jar", "build/libs/gateway-0.0.1.jar"]