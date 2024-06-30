FROM openjdk:17 AS build
WORKDIR /app
COPY . .
RUN chmod +x mvnw && ./mvnw clean package

FROM openjdk:17
COPY --from=build /app/target/link-shortener*.jar /usr/local/lib/link-shortener.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/link-shortener.jar"]