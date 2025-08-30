# ==========================
# 1. Build Stage (Maven)
# ==========================
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# ==========================
# 2. Runtime Stage (JDK)
# ==========================
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy only the final jar from the build stage
COPY --from=build /app/target/qrccodegeneratorall-0.0.1-SNAPSHOT.jar app.jar

# Expose port (Render uses dynamic PORT)
EXPOSE 8080

# Run the app
ENTRYPOINT ["java","-jar","app.jar"]
