#FROM openjdk:17-jdk-alpine
#WORKDIR /app

#COPY target/Hello_Pita_Dash_Service-0.0.1-SNAPSHOT.jar app.jar
##ADD target/pitadashservice.jar app.jar
##EXPOSE 5757
#8086
##COPY .env /
##ENTRYPOINT ["java","-jar","app.jar"]
#ENV MYSQL_USERNAME=root
#ENV MYSQL_PASSWORD=root
#ENV MYSQL_DATABASE=pitadash
#ENV SPRING_DATASOURCE_URL=jdbc:mysql://mysql2:3306/database?autoReconnect=true&useSSL=false

#RUN apk add --no-cache mysql-client




#"--spring.datasource.url=jdbc:mysql://mysql-container:3306/pitadash","--spring.datasource.username=root","--spring.datasource.password=root"

#docker run -p 8080:8080 -e MYSQL_USERNAME=root -e MYSQL_PASSWORD=root -e MYSQL_DATABASE=pitadash pitadashservice3
#http://192.168.0.235:3000