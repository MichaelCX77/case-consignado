FROM openjdk:11-jdk-slim
VOLUME /tmp
ADD consignado-eureka-server/target/consignado-eureka-server-0.0.1-SNAPSHOT.jar consignado-eureka-server.jar
EXPOSE 8761
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar", "/consignado-eureka-server.jar"]