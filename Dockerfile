FROM openjdk:17

ADD target/Cart_File.jar Cart_File.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","Cart_File.jar"]