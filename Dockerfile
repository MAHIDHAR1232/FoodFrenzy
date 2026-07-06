# Stage 1: Build the Spring Boot application
FROM eclipse-temurin:17-jdk-focal AS builder

# Set the working directory inside the container for the build stage.
WORKDIR /app

# Copy the Maven wrapper and project object model configuration file.
# This allows you to compile dependency trees without global Maven installations.
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Copy the source code.
# The `src` directory contains your controller structures, entities, and html templates.
COPY src src

# Make the Maven wrapper script executable.
RUN chmod +x mvnw

# Build the Spring Boot application into an executable JAR.
# `clean package` compiles everything and builds your target deployment jar.
# `-DskipTests` skips running tests during the Docker build process to speed things up.
RUN ./mvnw clean package -DskipTests

# Stage 2: Create the final, lightweight runtime image
FROM eclipse-temurin:17-jre-focal

# Set the working directory inside the container for the runtime stage.
WORKDIR /app

# Copy the executable JAR from the maven target folder in the builder stage to the final image.
# Using a wildcard (*) ensures it captures your exact snapshot jar name without breaking.
COPY --from=builder /app/target/*.jar app.jar

# Expose the port on which your Spring Boot application listens (8080 for FoodFrenzy).
EXPOSE 8080

# Define the command to run your application when the container starts.
<<<<<<< HEAD
ENTRYPOINT ["java", "-jar", "app.jar"]
=======
ENTRYPOINT ["java", "-jar", "app.jar"]
>>>>>>> ae096ac50ff5e2001951d00de22110d2ecd6307f
