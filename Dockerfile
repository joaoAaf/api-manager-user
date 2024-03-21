FROM eclipse-temurin:17-alpine
WORKDIR /app
COPY . .
RUN apk add maven
RUN mvn clean package
EXPOSE 8000
CMD [ "java", "-jar", "./target/api-manager-user-0.0.1-SNAPSHOT.jar" ]