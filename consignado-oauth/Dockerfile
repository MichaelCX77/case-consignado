FROM openjdk:11-jdk-slim
VOLUME /tmp
ADD consignado-oauth/target/consignado-oauth-0.0.1-SNAPSHOT.jar consignado-oauth.jar
EXPOSE 8080
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar", "/consignado-oauth.jar"]