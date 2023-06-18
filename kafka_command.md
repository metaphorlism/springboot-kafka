# create topic

```bash
docker exec broker kafka-topics --bootstrap-server broker:9092 --create --topic <topic-name>
```

# write message to topic

```bash
docker exec --interactive --tty broker kafka-console-producer --bootstrap-server broker:9092 --topic <topic-name>
```

# read messages from topic

```bash
docker exec --interactive --tty broker kafka-console-consumer --bootstrap-server broker:9092 --topic <topic-name> --from-beginning
```

# list kafka topics

```bash
docker exec broker kafka-topics --list --bootstrap-server broker:9092
```
