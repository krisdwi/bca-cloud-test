FROM maven:3.8.1-openjdk-17-slim

WORKDIR /build

# Build dependency offline to streamline build
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src src
RUN mvn clean install -PDev2 -o -Dmaven.test.skip=true

FROM registry.redhat.io/jboss-eap-7/eap73-openjdk8-openshift-rhel7

USER root

RUN yum install git -y

### DOWNLOAD FROM GIT 

RUN git clone -b main https://github.com/krisdwi/bca-cloud-test.git /tmp/apps

### COPY TO DEPLOYMENT

RUN cp //build/target/mycore-svc-int.war $JBOSS_HOME/standalone/deployments/mycore-svc-int.war

### ORACLE DB DRIVER 

RUN mkdir -p $JBOSS_HOME/modules/org/postgresql/main

RUN cp /tmp/apps/modules/org/postgresql/main/* $JBOSS_HOME/modules/org/postgresql/main

### CUSTOM CONFIG 

RUN cp /tmp/apps/config/standalone-openshift.xml $JBOSS_HOME/standalone/configuration/standalone-openshift.xml

### RUN JBOSS

RUN chown -R jboss:root $JBOSS_HOME && chmod -R ug+rwX $JBOSS_HOME

USER jboss 

CMD $JBOSS_HOME/bin/openshift-launch.sh