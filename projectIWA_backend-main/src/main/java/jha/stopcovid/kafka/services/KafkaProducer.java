package jha.stopcovid.kafka.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final String TOPIC= "topic-geoloc";

    @Autowired
    private org.springframework.kafka.core.KafkaTemplate<String, Object> KafkaTemplate;

    public void sendInformation(Object info, String topic) {
        this.KafkaTemplate.send(topic, info);
    }
}
