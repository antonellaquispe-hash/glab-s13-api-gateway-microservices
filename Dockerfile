FROM maven:3.9.11-eclipse-temurin-17 AS build
ARG MODULE
WORKDIR /workspace
COPY pom.xml ./
COPY eureka-server ./eureka-server
COPY gateway-service ./gateway-service
COPY categoria-service ./categoria-service
COPY producto-service ./producto-service
COPY cliente-service ./cliente-service
COPY pedido-service ./pedido-service
RUN mvn -B -pl ${MODULE} -am package -DskipTests

FROM eclipse-temurin:17-jre
ARG MODULE
WORKDIR /app
COPY --from=build /workspace/${MODULE}/target/${MODULE}-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
