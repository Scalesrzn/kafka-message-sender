package ru.yaropolk.mockkafkaclient.Service;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;

public class KafkaProducerService {

    KafkaProducer<String, String> kafkaProducer;

    public KafkaProducerService(String BOOTSTRAP_SERVERS){
        this.kafkaProducer = createProducer(BOOTSTRAP_SERVERS);
    }

    private KafkaProducer<String, String> createProducer(String BOOTSTRAP_SERVERS) {
        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "KTMS Client VGRADOV");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        return new KafkaProducer<>(props,new StringSerializer(), new StringSerializer());
    }

    public void SendMessage(String topic, String key, String message){
        ProducerRecord<String, String> producerRecord =
                new ProducerRecord<>(topic, key, message);
        kafkaProducer.send(producerRecord);

    }


    public void Disconnect(){
        kafkaProducer.flush();
        kafkaProducer.close();
    }
}
