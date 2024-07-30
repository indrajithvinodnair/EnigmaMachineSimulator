# First stage: build the application
FROM maven:3.9.8-amazoncorretto-17-al2023 AS build
#  Set the working directory
WORKDIR /app
#  Copy project source files into working direcoty
COPY . .
# Build the application
RUN mvn clean package -DskipTests


# Second stage: create image and run
FROM openjdk:17-jdk-alpine3.14
# Set the working directory
WORKDIR /app
# Copy the built application from the build stage
COPY --from=build /app/target/*.jar enigma.jar
# Expose the port
EXPOSE 8080
# Run the application
CMD ["java", "-jar", "enigma.jar"]



