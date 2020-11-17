package jha.stopcovid.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {


    @Autowired
    private org.springframework.kafka.core.KafkaTemplate<String, Object> KafkaTemplate;

    public void sendInformation(Object info, String topic) {
        this.KafkaTemplate.send(topic, info);
    }
}
