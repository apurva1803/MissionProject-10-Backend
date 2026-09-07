FROM tomcat:11

WORKDIR /app

COPY target/ORSProject-10-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8085

CMD ["java", "-jar", "app.jar"]