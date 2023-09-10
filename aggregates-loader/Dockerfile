FROM eclipse-temurin:20
ENV JAR_NAME=aggregates-loader.jar
COPY target/$JAR_NAME $JAR_NAME
ENTRYPOINT java -jar $JAR_NAME
