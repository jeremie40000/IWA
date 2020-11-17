package jha.stopcovid.controllers;

import jha.stopcovid.models.GeolocationData;
import jha.stopcovid.services.KafkaProducer;
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
    public void sendInformationToTopic(@RequestBody GeolocationData data) {
        this.kafkaProducer.sendInformation(data, "topic-geoloc");
    }
}
