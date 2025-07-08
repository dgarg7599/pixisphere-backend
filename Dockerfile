# Use official OpenJDK 21 image as base
FROM eclipse-temurin:21-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the built jar file from target folder
COPY target/pixisphere-backend-0.0.1-SNAPSHOT.jar app.jar

# Expose port (same as your Spring Boot server port)
EXPOSE 8085

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
