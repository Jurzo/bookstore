FROM openjdk:11-slim-buster as build

COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .

RUN chmod +x mvnw && ./mvnw -B dependency:go-offline

COPY src src

RUN --mount=type=secret,id=TEST_USERNAME \
   export TEST_USERNAME=$(cat /run/secrets/TEST_USERNAME) && \
   ./mvnw -B package

RUN echo -n $TEST_USERNAME > .env

FROM openjdk:11-jre-slim-buster

COPY --from=build target/bookstore-1.0.0.jar .
COPY --from=build .env .

EXPOSE 8080

ENTRYPOINT ["TEST_USERNAME=$(cat .env)", "java"]
CMD ["-jar", "bookstore-1.0.0.jar"]