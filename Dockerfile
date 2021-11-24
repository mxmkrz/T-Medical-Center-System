
FROM openjdk:17-jdk
COPY /target/sample.war sample.war
EXPOSE 8080
CMD ["java","-jar","/sample.war"]