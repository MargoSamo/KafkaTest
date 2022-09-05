package com.mariia.kafka.userinput;

import com.mariia.kafka.Settings;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.Scanner;

public class InputMain {
    private static final Logger log = LoggerFactory.getLogger(InputMain.class.getName());
    public static void main(String[] args) {
        log.info("Hello User input");
        String bootstrapServers = "127.0.0.1:9092";

        // create Producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        try (KafkaProducer<String, String> producer = new KafkaProducer<>(properties)) {

            // create a producer record
            var console = new Scanner(System.in);
            while (true) {
                log.info("Type message");
                var msg = console.nextLine();
                if (msg.equals("q")) {
                    break;
                }
                ProducerRecord<String, String> producerRecord =
                        new ProducerRecord<>(Settings.topic, msg);

                producer.send(producerRecord);
                log.info("Msg {} sent", msg);
            }
        }




    }
}
