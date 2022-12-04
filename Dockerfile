FROM openjdk:19-jdk

WORKDIR /home/vehicles-rest-service

ADD target/vehicles-rest-service-*.jar vehicles-rest-service.jar

ADD ./wait-for-it.sh wait-for-it.sh

RUN bash -c "chmod 755 wait-for-it.sh"