package jha.stopcovid.kafka.controllers;

import jha.stopcovid.kafka.services.KafkaProducer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class KafkaController {

    private final KafkaProducer kafkaProducer;

    public KafkaController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }
    @PostMapping("/publish")
    public void sendInformationToTopic(@RequestBody Object data) {
        this.kafkaProducer.sendInformation(data, "topic-geoloc");
    }
}
