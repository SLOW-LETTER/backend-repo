FROM openjdk:11-jdk
# 기본 이미지

VOLUME /tmp
# 데이터 보존을 위한 Volume 마운트

EXPOSE 8080 8080
#외부에 노출할 포트

ARG JAR_FILE=/build/libs/postgresql-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} batch.jar

# 실행 명령
ENTRYPOINT ["java","-Djava.security.egd", "-Xdebug","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005","-jar","/batch.jar"]
