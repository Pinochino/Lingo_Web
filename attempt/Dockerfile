FROM maven:3.8.5-openjdk-17-slim AS build
WORKDIR /app

COPY ./common-library ./common-library
RUN mvn -f ./common-library/pom.xml clean install -DskipTests


COPY ./attempt ./attemptservice
RUN mvn -f ./attemptservice/pom.xml clean package -DskipTests


FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

COPY --from=build /app/attemptservice/target/*.jar attemptservice.jar

EXPOSE 9003
ENTRYPOINT ["java", "-jar", "attemptservice.jar"]