FROM eclipse-temurin:17-jdk-jammy

COPY target/ezpark-0.0.1-SNAPSHOT.jar ezpark-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "ezpark-0.0.1-SNAPSHOT.jar"]