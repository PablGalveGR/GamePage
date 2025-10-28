FROM eclipse-temurin:21.0.8_9-jdk-jammy
WORKDIR /app
COPY . .
RUN ./mvnw clean package -Dskiptests
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
