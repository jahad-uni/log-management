#FROM maven:3.6.0-jdk-11-slim AS build
#
#COPY . /app
#WORKDIR /app
#
#RUN mvn clean install
#
#FROM adoptopenjdk:11-jre-hotspot as builder
#COPY --from=build /app/log-management-app/target/*.jar log-management-app.jar
#RUN java -Djarmode=layertools -jar log-management-app.jar extract
#
#FROM adoptopenjdk:11-jre-hotspot
#COPY --from=builder dependencies/ ./
#COPY --from=builder snapshot-dependencies/ ./
#COPY --from=builder spring-boot-loader/ ./
#COPY --from=builder application/ ./
#ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]



FROM adoptopenjdk:11-jre-hotspot as builder
ARG JAR_FILE=log-management-app/target/*.jar
COPY ${JAR_FILE} log-management-app.jar
RUN java -Djarmode=layertools -jar log-management-app.jar extract

FROM adoptopenjdk:11-jre-hotspot
COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
