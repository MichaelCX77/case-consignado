FROM openjdk:11-jdk-slim
VOLUME /tmp
ADD consignado-gateway/target/consignado-api-gateway-0.0.1-SNAPSHOT.jar consignado-api-gateway.jar
EXPOSE 8765
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar", "/consignado-api-gateway.jar"]