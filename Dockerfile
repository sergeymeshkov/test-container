FROM sapmachine:17

ENV DOCKER_HOST=tcp://dind-service:2375

COPY ./gradle /tmp-build/gradle/
COPY ./src /tmp-build/src/
COPY ./build.gradle /tmp-build/build.gradle
COPY ./gradlew /tmp-build/gradlew
COPY ./gradlew.bat /tmp-build/gradlew.bat
COPY ./settings.gradle /tmp-build/settings.gradle
WORKDIR /tmp-build
RUN ./gradlew clean build

#use multistage build and copy jar file from build image
#COPY build/libs/test-container-0.0.1-SNAPSHOT.jar /opt/test-container.jar
#RUN rm -r /tmp-build

#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "/opt/test-container.jar"]