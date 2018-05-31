FROM java:8
VOLUME /tmp
ADD target/microservice-ecms-gateway-service-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8765
ENTRYPOINT ["java", "-jar", "/app.jar"]