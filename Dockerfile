# Use a slim OpenJDK 21 base image
FROM eclipse-temurin:21-jdk-jammy

# Add a volume pointing to /tmp
VOLUME /tmp

# Expose the application's port
EXPOSE 8445

# The application's jar file (built by Maven or Gradle)
ARG JAR_FILE=target/*.jar

# Copy the jar file into the container
COPY ${JAR_FILE} app.jar

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app.jar"]