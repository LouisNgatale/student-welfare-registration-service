FROM openjdk:11
ADD target/student-welfare-registration-service.jar student-welfare-registration-service.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","student-welfare-registration-service.jar"]
