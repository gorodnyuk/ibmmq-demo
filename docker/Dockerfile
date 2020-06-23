FROM ibmcom/mq:9.1.5.0-r1 AS mq-dev-server
COPY config.mqsc /etc/mqm/
ENTRYPOINT ["runmqdevserver"]