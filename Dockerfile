FROM maven:3.8.5-openjdk-17 as BUILD
WORKDIR /build
COPY pom.xml .
RUN mvn clean
RUN mvn compiler:help jar:help resources:help surefire:help clean:help install:help deploy:help site:help dependency:help javadoc:help spring-boot:help
RUN mvn dependency:go-offline
COPY src/ /build/src/
RUN mvn package

FROM openjdk:17-alpine
ENV APP_FILE booking-service.jar
ENV APP_HOME /usr/apps
EXPOSE 8080
COPY --from=BUILD /build/target/$APP_FILE $APP_HOME/
WORKDIR $APP_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar $APP_FILE"]