FROM registry.redhat.io/jboss-eap-7/eap73-openjdk8-openshift-rhel7

USER root

### COPY TO DEPLOYMENT

RUN cp target/mono.war $JBOSS_HOME/standalone/deployments/mycore-svc-int.war

### ORACLE DB DRIVER 

RUN mkdir -p $JBOSS_HOME/modules/org/postgresql/main

RUN cp modules/org/postgresql/main/* $JBOSS_HOME/modules/org/postgresql/main

### CUSTOM CONFIG 

RUN cp config/standalone-openshift.xml $JBOSS_HOME/standalone/configuration/standalone-openshift.xml

### RUN JBOSS

RUN chown -R jboss:root $JBOSS_HOME && chmod -R ug+rwX $JBOSS_HOME

USER jboss 

CMD $JBOSS_HOME/bin/openshift-launch.sh
