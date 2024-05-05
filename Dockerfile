FROM ubuntu:latest AS build

RUN apt-get update && apt-get install -y openjdk-17-jdk maven

RUN apt-get install -y curl && \
    curl -sL https://deb.nodesource.com/setup_14.x | bash - && \
    apt-get install -y nodejs

RUN npm install -g @angular/cli@17.3.6

WORKDIR /app

COPY . .

WORKDIR /app/Angular-sistem-de-presenca
RUN npm install && ng build --prod

WORKDIR /app

RUN mvn clean install

FROM openjdk:17-jdk-slim

EXPOSE 8080

WORKDIR /app

COPY --from=build /app/target/sistema-de-presenca-1.0.0.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
