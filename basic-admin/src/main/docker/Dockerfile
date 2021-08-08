FROM java:8-alpine
VOLUME /logs

ADD target/basic-admin.jar  basic-admin.jar

ENV LANG C.UTF-8
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
EXPOSE 8006
ENTRYPOINT ["java","-jar","basic-admin.jar"]
#ENTRYPOINT ["java","-jar","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=9000","-Dspring.profiles.active=sit","basic-auth.jar"]
