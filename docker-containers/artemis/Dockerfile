FROM airhacks/java
LABEL maintainer="Adam Bien, adam-bien.com" description="Artemis Messaging / JMS"
ENV INSTALL_DIR /opt
ENV VERSION 2.17.0
ENV USER dan2021
ENV PASSWORD dan2021
ENV ARTEMIS_HOME ${INSTALL_DIR}/apache-artemis-${VERSION}
ENV BROKER_HOME ${INSTALL_DIR}/brokers/
RUN useradd -b /opt -m -s /bin/sh -d ${INSTALL_DIR} serveradmin && echo serveradmin:serveradmin | chpasswd
RUN curl -O https://repository.apache.org/content/repositories/releases/org/apache/activemq/apache-artemis/${VERSION}/apache-artemis-${VERSION}-bin.tar.gz \
    && tar zxf apache-artemis-${VERSION}-bin.tar.gz -C ${INSTALL_DIR} \
    && mkdir ${BROKER_HOME} \
    && ${ARTEMIS_HOME}/bin/artemis create ${BROKER_HOME}/airhacks --user ${USER} --password ${PASSWORD} --allow-anonymous --http-host 0.0.0.0 --relax-jolokia \
    && rm apache-artemis-${VERSION}-bin.tar.gz \
    && chown -R serveradmin:serveradmin /opt \
    && chmod -R a+rw ${INSTALL_DIR}
ADD broker.xml  /opt/brokers/airhacks/etc/broker.xml
USER serveradmin
ENTRYPOINT ${BROKER_HOME}/airhacks/bin/artemis run
EXPOSE 61616 8161 5445 5672 1883 61613