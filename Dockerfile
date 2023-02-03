FROM gradle:jdk19-alpine AS build

WORKDIR /build

COPY build.gradle settings.gradle gradle.properties ./
COPY src ./src

RUN ["gradle", "clean", "bootJar"]

FROM eclipse-temurin:19-jre-alpine as prod

WORKDIR /app

COPY --from=build /build/build/libs/*.jar ./app.jar 

EXPOSE 80

ENTRYPOINT [ "java", "-jar", "app.jar" ]
