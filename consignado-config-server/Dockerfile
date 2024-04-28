FROM openjdk:11-jdk-slim
VOLUME /tmp
ADD consignado-config-server/target/consignado-config-server-0.0.1-SNAPSHOT.jar consignado-config-server.jar
EXPOSE 8888
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar", "/consignado-config-server.jar"]