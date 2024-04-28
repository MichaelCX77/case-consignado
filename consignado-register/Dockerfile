FROM openjdk:11-jdk-slim
VOLUME /tmp
ADD consignado-register/target/consignado-register-0.0.1-SNAPSHOT.jar consignado-register.jar
EXPOSE 8080
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar", "/consignado-register.jar"]