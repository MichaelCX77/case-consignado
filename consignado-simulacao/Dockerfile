FROM openjdk:11-jdk-slim
VOLUME /tmp
ADD consignado-simulacao/target/consignado-simulacao-0.0.1-SNAPSHOT.jar consignado-simulacao.jar
EXPOSE 8080
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar", "/consignado-simulacao.jar"]