FROM sapmachine:17


ADD ./gradle /tmp-build/gradle/
ADD ./src /tmp-build/src/
ADD ./build.gradle /tmp-build/build.gradle
ADD ./gradlew /tmp-build/gradlew
ADD ./gradlew.bat /tmp-build/gradlew.bat
ADD ./settings.gradle /tmp-build/settings.gradle

WORKDIR /tmp-build
RUN ./gradlew clean build
COPY build/libs/test-container-0.0.1-SNAPSHOT.jar /opt/test-container.jar
RUN rm -r /tmp-build

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/test-container.jar"]