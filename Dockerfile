FROM default-route-openshift-image-registry.apps.ocpdev.dti.co.id/mycore-dev/maven:3.8.2-jdk-8 AS build_java

WORKDIR /build

# Build dependency offline to streamline build
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src src
RUN mvn clean package -Dmaven.test.skip=true

#FROM openjdk:11-jdk
COPY --from=0 /build/target/demo-0.0.1-SNAPSHOT.jar /app/target/demo-0.0.1-SNAPSHOT.jar

EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/app/target/pricing.jar", "--server.port=8080" ]
