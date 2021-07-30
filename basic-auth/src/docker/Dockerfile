FROM openjdk:8u181-jdk-alpine
VOLUME /logs

ADD target/basic-auth.jar  basic-auth.jar

ENV LANG C.UTF-8
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
EXPOSE 5000
ENTRYPOINT ["java","-jar","basic-auth.jar"]
#ENTRYPOINT ["java","-jar","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=4999","-Dspring.profiles.active=sit","basic-auth.jar"]
