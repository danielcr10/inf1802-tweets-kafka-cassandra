# Projeto final Tweets-kafka-cassandra

## Instrucoes

Para preparacao do ambiente

### Comando:

`docker run -d --name cassandra-db -m 1024M --net=host cassandra:3`

Deve rodar o docker-compose com kafka e zookeeper:

```
$ cd images-docker-kafka/examples/kafka-single-node/
$ docker-compose up -d
```

Deve criar os topicos do kafka:

```
$ docker-compose exec kafka bash
$ kafka-topics --zookeeper $KAFKA_ZOOKEEPER_CONNECT --create --topic tweets --partitions 3 --replication-factor 1
$ kafka-console-consumer --bootstrap-server localhost:9092 --topic tweets --group tweets_application
```

Executar as aplicacoes produtor e consumidor. Com as duas rodando chamar a api rest.
Para o produtor:

`curl http://localhost:8080/tweets/collector`

Para consumidor:

`curl http://localhost:8090/tweets/consumer`

Aplicacoes em execucao:

![Application](https://github.com/danielcr10/inf1802-tweets-kafka-cassandra/blob/master/images/application.png)

Para visualizar o Cassandra:

```
$ docker exec -it cassandra-db /bin/bash
$ cqlsh
cqlsh> use tweets;
cqlsh:tweets> select * from Tweets;
```

Cassandra em execucao:

![Cassandra](https://github.com/danielcr10/inf1802-tweets-kafka-cassandra/blob/master/images/cassandra.png)

[application]: https://github.com/danielcr10/inf1802-tweets-kafka-cassandra/blob/master/images/application.png "Aplicacao rodando"
[cassandra]: https://github.com/danielcr10/inf1802-tweets-kafka-cassandra/blob/master/images/cassandra.png "Console cassandra"
