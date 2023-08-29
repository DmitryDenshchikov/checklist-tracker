FROM eclipse-temurin:17-jdk

COPY ./build/libs /app
COPY ./cert /app

ARG issuer_url
ENV ISSUER_URL $issuer_url

ARG key_alias
ENV KEY_ALIAS $key_alias

ARG key_store_path
ENV KEY_STORE_PATH $key_store_path

ARG keystore_pass
ENV KEY_STORE_PASSWORD $keystore_pass

ARG db_host
ENV DATABASE_HOST $db_host

ARG db_port
ENV DATABASE_PORT $db_port

ARG db_name
ENV DATABASE_NAME $db_name

ARG db_username
ENV DATABASE_USER_NAME $db_username

ARG db_pass
ENV DATABASE_USER_PASSWORD $db_pass

EXPOSE 9000
ENTRYPOINT java -jar /app/auth-server.jar
