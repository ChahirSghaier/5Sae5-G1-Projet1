FROM openjdk:8
EXPOSE 8089
ADD target/StationSki-0.0.1-SNAPSHOT.jar StationSki-test.jar
ENTRYPOINT ["java","-jar","/StationSki-test-test.jar "]