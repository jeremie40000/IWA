package jha.stopcovid.kafka.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics="topic-geoloc", groupId ="my_group_id")
    public void getInformationGeolocation(Object info) {
        System.out.println(info);
    }
}
