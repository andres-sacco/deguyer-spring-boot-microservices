FROM openjdk:21-slim-bookworm AS builder
WORKDIR workspace
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} api-reservation.jar
RUN java -Djarmode=layertools -jar api-reservation.jar extract

FROM openjdk:21-slim-bookworm
WORKDIR workspace
COPY --from=builder workspace/dependencies/ ./
COPY --from=builder workspace/spring-boot-loader/ ./
COPY --from=builder workspace/snapshot-dependencies/ ./
COPY --from=builder workspace/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher", "--spring.config.location=classpath:/application-docker.yml"]