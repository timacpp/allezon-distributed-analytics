FROM eclipse-temurin:20
ENV JAR_NAME=user-tags-api.jar
COPY target/$JAR_NAME $JAR_NAME
ENTRYPOINT java -jar $JAR_NAME