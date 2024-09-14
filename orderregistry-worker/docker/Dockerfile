FROM amazoncorretto:22
LABEL authors="thiagormg"

WORKDIR /app
COPY orderregistry-worker-0.0.1-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]