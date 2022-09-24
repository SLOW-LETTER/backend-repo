FROM openjdk:11-jdk
# 기본 이미지

#LABEL maintainer="jiys@tidesquare.com"

VOLUME /tmp
# 데이터 보존을 위한 Volume 마운트

EXPOSE 8080 8080
#외부에 노출할 포트

ARG JAR_FILE=/build/libs/postgresql-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} batch.jar

# 실행 명령
ENTRYPOINT ["java","-Djava.security.egd", "-Xdebug","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005","-jar","/batch.jar"]

# 출처: https://ysjee141.github.io/blog/dev%20tools/intellij-springboot-docker/ [happs's doodle]

#FROM openjdk:11
#ENV APP_HOME=/usr/app/
#WORKDIR $APP_HOME
#COPY ./build/libs/* ./app.jar
#EXPOSE 8080
#CMD ["java","-jar","app.jar"]

#FROM gradle:4.7.0-jdk11-alpine AS build
#COPY --chown=gradle:gradle . /home/gradle/src
#WORKDIR /home/gradle/src
#RUN gradle build --no-daemon
#
#FROM openjdk:8-jre-slim
#
#EXPOSE 8080
#
#RUN mkdir /app
#
#COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar
#
#ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/spring-boot-application.jar"]

#FROM openjdk:11
#VOLUME /tmp
#ARG JAR_FILE
#COPY ${JAR_FILE} /gradle/wrapper/*
#ENTRYPOINT ["java","-jar","/gradle-wrapper.jar"]

##FROM adoptopenjdk/openjdk11
##CMD ["./mvnw", "clean", "package"]
##ARG JAR_FILE_PATH=target/*.jar
##COPY ${JAR_FILE_PATH} app.jar
##ENTRYPOINT ["java", "-jar", "app.jar"]
#
##FROM adoptopenjdk/openjdk11
##ARG JAR_FILE=gradle/wrapper/*.jar
##COPY ${JAR_FILE} app.jar
##ENTRYPOINT ["java", "-jar", "app.jar"]
#
#
##FROM openjdk:11 AS builder
##COPY gradlew .
##COPY gradle gradle
##COPY build.gradle .
##COPY settings.gradle .
##COPY src src
##RUN chmod +x ./gradlew
##RUN ./gradlew bootjar
##
##FROM openjdk:11
##COPY --from=builder build/libs/*jar app.jar
##
##ENTRYPOINT ["java", "-jar", "/app.jar"]
#
##FROM openjdk:11
##ARG JAR_FILE=build/resources/main/*.jar
##COPY ${JAR_FILE} app.jar
##
##EXPOSE 8080/tcp
##ENTRYPOINT ["java","-jar","/app.jar"]
#
#
#### build stage ###
#FROM openjdk:11 AS builder
#
## set arg
#ARG WORKSPACE=/home/spring-docker
#ARG BUILD_TARGET=${WORKSPACE}/build/libs
#WORKDIR ${WORKSPACE}
#
## copy code & build
#COPY . .
#RUN ./gradlew clean bootJar
#
## unpack jar
#WORKDIR ${BUILD_TARGET}
#RUN jar -xf *.jar
#
#
#### create image stage ###
#FROM openjdk:11
#
#ARG WORKSPACE=/home/spring-docker
#ARG BUILD_TARGET=${WORKSPACE}/build/libs
#ARG DEPLOY_PATH=${WORKSPACE}/deploy
#
## copy from build stage
#COPY --from=builder ${BUILD_TARGET}/org ${DEPLOY_PATH}/org
#COPY --from=builder ${BUILD_TARGET}/BOOT-INF/lib ${DEPLOY_PATH}/BOOT-INF/lib
#COPY --from=builder ${BUILD_TARGET}/META-INF ${DEPLOY_PATH}/META-INF
#COPY --from=builder ${BUILD_TARGET}/BOOT-INF/classes ${DEPLOY_PATH}/BOOT-INF/classes
#
#WORKDIR ${DEPLOY_PATH}
#
#EXPOSE 8080/tcp
#ENTRYPOINT ["java","org.springframework.boot.loader.JarLauncher"]
