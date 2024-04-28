FROM openjdk:11-jdk-slim
VOLUME /tmp
ADD consignado-cliente/target/consignado-cliente-0.0.1-SNAPSHOT.jar consignado-cliente.jar
EXPOSE 8080
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar", "/consignado-cliente.jar"]