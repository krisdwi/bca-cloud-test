FROM default-route-openshift-image-registry.apps.ocpdev.dti.co.id/mycore-dev/mycore-api-cloud-dev AS build_java

USER root

RUN yum install git -y

### DOWNLOAD FROM GIT 

RUN git clone -b main https://github.com/krisdwi/bca-cloud-test.git /tmp/apps

### COPY TO DEPLOYMENT

RUN cp target/mono.war $JBOSS_HOME/standalone/deployments/mycore-svc-int.war

### ORACLE DB DRIVER 

RUN mkdir -p $JBOSS_HOME/modules/org/postgresql/main

RUN cp /tmp/apps/modules/org/postgresql/main/* $JBOSS_HOME/modules/org/postgresql/main

### CUSTOM CONFIG 

RUN cp /tmp/apps/config/standalone-openshift.xml $JBOSS_HOME/standalone/configuration/standalone-openshift.xml

### RUN JBOSS

RUN chown -R jboss:root $JBOSS_HOME && chmod -R ug+rwX $JBOSS_HOME

USER jboss 

CMD $JBOSS_HOME/bin/openshift-launch.sh