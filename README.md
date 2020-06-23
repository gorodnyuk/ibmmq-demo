Демонстрация работы с очередями IBM-MQ
Основной образ - https://hub.docker.com/r/ibmcom/mq
***************************************************
В директории docker находится все необходимое для начала работы с очереди (без последующей настройки)

Dockerfile - сборка брокера с настроенными очередями - ORDER.REQUEST, ORDER.RESPONSE

Собрать, если нет:
docker build -t ibmmq-local .

Запустить:
docker run --name {container-name} --env LICENSE=accept --env MQ_QMGR_NAME=QM1 -p 1414:1414 -p 9443:9443 --detach ibmmq-local

Консоль:
https://localhost:9443/ibmmq/console

Креды для входа (по-умолчанию):
admin
passw0rd
