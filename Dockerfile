FROM openjdk:11-jre-alpine
EXPOSE 8080
WORKDIR application
COPY COPY build/libs/*.jar ./app.jar/
ENTRYPOINT ["java", "-jar", "./app.jar"]