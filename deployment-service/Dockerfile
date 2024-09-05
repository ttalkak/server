FROM openjdk:17-ea-11-jdk-slim
RUN apt-get update && apt-get install -y procps

COPY . .

RUN sh gradlew build

##RUN chmod -R 777 build/libs/deployment-0.0.1.jar
## WORKDIR build/libs

ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "build/libs/deployment-0.0.1.jar"]