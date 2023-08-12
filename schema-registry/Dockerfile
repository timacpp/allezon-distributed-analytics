FROM maven:3.9.3-eclipse-temurin-20

ENV VERSION=1.1.5
ENV PACKAGE_NAME=spring-cloud-schema-registry-server
ENV JAR_NAME=${PACKAGE_NAME}-${VERSION}.jar

RUN wget https://repo.maven.apache.org/maven2/org/springframework/cloud/${PACKAGE_NAME}/${VERSION}/${JAR_NAME}

ENTRYPOINT java -jar ${JAR_NAME}