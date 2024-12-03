FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY target/Forumeong-0.0.1-SNAPSHOT.jar ./app.jar

COPY serviceAccountKey.json ./

EXPOSE 8080

CMD ["java","-jar","app.jar"]