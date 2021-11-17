FROM openjdk:17-jdk
COPY /target/T_Medical_Center_System-0.0.1-SNAPSHOT.jar t_medical_center_system.jar
EXPOSE 8080
CMD ["java","-jar","/t_medical_center_system.jar"]
#FROM tomcat:latest
#ADD target/sample.war /usr/local/tomcat/webapps/
#EXPOSE 8080
#CMD ["catalina.sh", "run"]
