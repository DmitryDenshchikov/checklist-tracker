FROM eclipse-temurin:17-jdk

COPY ./build/libs /app
COPY ./cert /app

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

ARG auth_server_url
ENV AUTH_SERVER_URL $auth_server_url

EXPOSE 8443

RUN keytool -srcstorepass ${KEY_STORE_PASSWORD} -storepass ${KEY_STORE_PASSWORD} -v -importkeystore -srckeystore ${KEY_STORE_PATH} -srcstoretype PKCS12 -destkeystore /opt/java/openjdk/lib/security/cacerts -deststoretype JKS
CMD java -jar /app/app.jar
