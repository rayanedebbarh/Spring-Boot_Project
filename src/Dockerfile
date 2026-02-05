# Stage 1: Build the application
FROM maven:3.9.5-eclipse-temurin-17 AS build

# Set working directory inside container
WORKDIR /app

# Copy pom.xml first (for better Docker layer caching)
COPY pom.xml .

# Download dependencies (this layer will be cached if pom.xml doesn't change)
RUN mvn dependency:go-offline

# Copy the rest of the source code
COPY main ./src/main

# Build the application (skip tests for faster builds)
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM eclipse-temurin:17-jre

# Set working directory
WORKDIR /app

# Copy the JAR file from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port 8080 (Spring Boot default port)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]