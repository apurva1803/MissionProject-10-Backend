FROM tomcat:11

RUN sed -i 's/port="8080"/port="8888"/g' /usr/local/tomcat/conf/server.xml

WORKDIR /app

COPY target/ORSProject-10-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8888

CMD ["java", "-jar", "app.jar"]