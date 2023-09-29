FROM openjdk:22-slim
LABEL authors="Marcos Sousa"
WORKDIR /app
COPY target/prospect-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]