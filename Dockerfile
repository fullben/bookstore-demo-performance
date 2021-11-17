FROM maven:3.6-openjdk-8 AS BUILD_IMAGE
WORKDIR /home/bookstore
COPY src /home/bookstore/src
COPY pom.xml /home/bookstore
RUN mvn clean package

FROM openjdk:8
WORKDIR /home/bookstore
COPY --from=BUILD_IMAGE /home/bookstore/target/bookstore-demo-performance-*.jar app.jar
EXPOSE 80
ENV JAVA_OPTS=""
CMD java $JAVA_OPTS -jar app.jar
