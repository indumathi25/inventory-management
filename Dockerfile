FROM openjdk:8
#ADD target/inventory-management inventory-management.jar
ADD *.jar app.jar
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "app.jar"]