FROM eclipse-temurin:24-jdk-alpine

WORKDIR /app

COPY build/libs/inventory-service-0.0.1-SNAPSHOT.jar inventory-service.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "inventory-service.jar"]
