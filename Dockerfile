FROM openjdk:8-jdk-alpine
ENV PORT 8080
EXPOSE 8080
COPY target/*.jar /opt/springsecurityexample.jar
WORKDIR /opt
CMD ["java", "-jar", "app.jar"]