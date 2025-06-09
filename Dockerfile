FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY target/your-app.jar app.jar
EXPOSE 8080
CMD ["java", "-Xmx128m", "-Xms64m", "-XX:+UseSerialGC", "-jar", "app.jar"]
