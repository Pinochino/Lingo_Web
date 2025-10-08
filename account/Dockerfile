FROM maven:3.8.5-openjdk-17-slim AS build
WORKDIR /app

COPY ./common-library ./common-library
RUN mvn -f ./common-library/pom.xml clean install -DskipTests


COPY ./account ./accountservice
RUN mvn -f ./accountservice/pom.xml clean package -DskipTests


FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

COPY --from=build /app/accountservice/target/*.jar accountservice.jar

EXPOSE 9001
ENTRYPOINT ["java", "-jar", "accountservice.jar"]