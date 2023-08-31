FROM openjdk:20-jre
ENV JAR_NAME=user-profiles.api.jar
COPY src/target/$JAR_NAME $JAR_NAME
ENTRYPOINT java -jar $JAR_NAME