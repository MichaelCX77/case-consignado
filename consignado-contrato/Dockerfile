FROM openjdk:11-jdk-slim
VOLUME /tmp
ADD consignado-contrato/target/consignado-contrato-0.0.1-SNAPSHOT.jar consignado-contrato.jar
EXPOSE 8080
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar", "/consignado-contrato.jar"]