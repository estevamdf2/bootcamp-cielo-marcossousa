FROM openjdk:11-jre-slim
LABEL authors="Marcos Sousa"
WORKDIR /app
COPY target/prospect.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]