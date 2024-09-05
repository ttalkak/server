FROM openjdk:17-ea-11-jdk-slim
RUN apt-get update && apt-get install -y procps

COPY . .


RUN sh gradlew build

RUN chmod -R 777 build/libs/config-0.0.1.jar

EXPOSE 8888

ENTRYPOINT ["java", "-jar", "build/libs/config-0.0.1.jar"]