FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} test.appgate.jar
ENTRYPOINT ["java","-jar","/test.appgate.jar"]