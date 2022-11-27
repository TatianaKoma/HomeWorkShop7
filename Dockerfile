FROM openjdk:8-jdk-alpine AS builder
WORKDIR target/dependency
ARG APPWAR=target/*.war
COPY ${APPWAR} app.war
RUN jar -xf ./app.war
RUN mv WEB-INF/lib-provided lib-provided
RUN mv WEB-INF/lib lib

FROM openjdk:8-jre-alpine
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY --from=builder ${DEPENDENCY}/lib /app/WEB-INF/lib
COPY --from=builder ${DEPENDENCY}/lib-provided /app/WEB-INF/lib-provided
COPY --from=builder ${DEPENDENCY}/org /app/org
COPY --from=builder ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=builder ${DEPENDENCY}/WEB-INF /app/WEB-INF

ENTRYPOINT ["java","-cp","/app/WEB-INF/classes:/app/WEB-INF/lib/*:/app/WEB-INF/lib-provided/*","com.example.homeworkshop7.HomeWorkShop7Application"]
