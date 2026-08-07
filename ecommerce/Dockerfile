FROM maven:3.9-eclipse-temurin-25 AS build

WORKDIR /app
COPY pom.xml .
RUN mvn -B dependency:go-offline

COPY src ./src
RUN mvn -B clean package -DskipTests

FROM eclipse-temurin:25-jre

WORKDIR /app
COPY --from=build /app/target/ecommerce-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java -Dserver.port=${PORT:-8080} -jar /app/app.jar"]
