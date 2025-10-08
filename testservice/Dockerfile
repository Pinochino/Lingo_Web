FROM maven:3.8.5-openjdk-17-slim AS build
WORKDIR /app

COPY ./common-library ./common-library
RUN mvn -f ./common-library/pom.xml clean install -DskipTests


COPY ./testservice ./testservice
RUN mvn -f ./testservice/pom.xml clean package -DskipTests


FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

COPY --from=build /app/testservice/target/*.jar testservice.jar

EXPOSE 9002
ENTRYPOINT ["java", "-jar", "testservice.jar"]