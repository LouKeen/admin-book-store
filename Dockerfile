FROM openjdk:8
ADD build/libs/book-0.0.1-SNAPSHOT.jar book-0.0.1-SNAPSHOT.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "book-0.0.1-SNAPSHOT.jar"]