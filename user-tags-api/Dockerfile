FROM maven/3.9.3-eclipse-temurin-20 as builder
COPY src .
ENTRYPOINT mvn spring-boot:run

FROM eclipse-temurin
COPY --from=builder src/target/user-tags-api.jar user-tags-api.jar
ENTRYPOINT java -jar user-tags-api.jar