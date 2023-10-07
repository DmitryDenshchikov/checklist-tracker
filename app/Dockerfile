FROM eclipse-temurin:17-jdk

COPY ./build/libs /app
COPY ./cert /app

ARG key_alias
ENV KEY_ALIAS $key_alias

ARG key_store_path
ENV KEY_STORE_PATH $key_store_path

ARG keystore_pass
ENV KEY_STORE_PASSWORD $keystore_pass

EXPOSE 8443

RUN keytool -srcstorepass ${KEY_STORE_PASSWORD} -storepass ${KEY_STORE_PASSWORD} -v -importkeystore -srckeystore ${KEY_STORE_PATH} -srcstoretype PKCS12 -destkeystore /opt/java/openjdk/lib/security/cacerts -deststoretype JKS
ENTRYPOINT java -jar /app/app.jar
