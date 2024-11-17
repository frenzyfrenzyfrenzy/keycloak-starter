FROM openjdk:21-jdk-slim
WORKDIR /app
COPY build/libs/backend-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
EXPOSE 5005

ENTRYPOINT ["java", "-Xdebug", "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=*:5005" , "-jar", "/app/app.jar"]