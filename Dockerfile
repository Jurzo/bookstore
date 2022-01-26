FROM openjdk:11-slim-buster as build

COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .

RUN --mount=type=secret,id=USERNAME \
   export USERNAME=$(cat /run/secrets/USERNAME)

RUN chmod +x mvnw && ./mvnw -B dependency:go-offline

COPY src src

RUN ./mvnw -B package

FROM openjdk:11-jre-slim-buster

COPY --from=build target/bookstore-1.0.0.jar .

EXPOSE 8080

ENTRYPOINT ["java"]
CMD ["-jar", "bookstore-1.0.0.jar"]