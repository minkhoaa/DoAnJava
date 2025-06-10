# Image nhẹ, chỉ runtime
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-Xmx128m", "-Xms64m", "-XX:+UseSerialGC", "-jar", "app.jar"]
