FROM openjdk:11
ADD target/inventory-management inventory-management.jar
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "inventory-management.jar"]