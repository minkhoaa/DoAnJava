# Giai đoạn 1: Build ứng dụng
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Tăng tốc build cache Maven bằng cách tách copy pom.xml
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source sau khi đã cache dependency
COPY src ./src

# Build ứng dụng (tạo file jar)
RUN mvn clean package -DskipTests

# Giai đoạn 2: Image chạy nhẹ hơn (dùng JRE nhẹ)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy jar từ giai đoạn build
COPY --from=build /app/target/*.jar app.jar

# Mở cổng (Swagger UI thường ở 8080/swagger-ui.html)
EXPOSE 8080

# Run app
ENTRYPOINT ["java", "-jar", "app.jar"]
