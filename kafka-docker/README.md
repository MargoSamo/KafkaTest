https://developer.confluent.io/quickstart/kafka-docker/

To start:

docker-compose up

To create a topic

docker exec broker kafka-topics --bootstrap-server broker:9092 --create --topic demo_msg_topic