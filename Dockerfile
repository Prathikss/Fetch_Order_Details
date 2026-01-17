# ===== Stage 1: Build JAR =====
FROM eclipse-temurin:17-jdk AS builder
WORKDIR /workspace

# Copy the entire project
COPY . .

# Make mvnw executable
RUN chmod +x mvnw

# Build jar
RUN ./mvnw clean package -DskipTests

# ===== Stage 2: Run JAR =====
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy jar from stage 1
COPY --from=builder /workspace/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]
