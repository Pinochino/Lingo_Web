FROM maven:3.8.5-openjdk-17-slim AS build
WORKDIR /app

COPY ./common-library ./common-library
RUN mvn -f ./common-library/pom.xml clean install -DskipTests


COPY ./api-gateway ./api-gateway
RUN mvn -f ./api-gateway/pom.xml clean package -DskipTests


FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

COPY --from=build /app/api-gateway/target/*.jar apigateway.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "apigateway.jar"]