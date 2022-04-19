#define base docker image
FROM openjdk:11
LABEL maintainer="javakrithika.net"
#jar file into dicker image
ADD target/jukeboxApi-0.0.1-SNAPSHOT.jar jukeboxapi.jar
#HOW THIS APP IS GOING TO RUN IN A DOCKER CONTAINER
ENTRYPOINT ["java", "-jar", "jukeboxapi.jar"]