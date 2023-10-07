FROM eclipse-temurin:17-jdk

COPY ./build/libs /app
COPY ./cert /app

EXPOSE 9000

ENTRYPOINT java -jar /app/auth-server.jar
