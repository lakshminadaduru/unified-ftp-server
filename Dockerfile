FROM  adoptopenjdk/openjdk11
RUN mkdir -p /opt/app
WORKDIR /opt/app
COPY target/springboot-ftpserver-0.0.1-SNAPSHOT.jar /opt/app
CMD ["java","-jar","springboot-ftpserver-0.0.1-SNAPSHOT.jar"]