package jha.stopcovid.geolocation.controllers;

import jha.stopcovid.geolocation.models.GeolocationData;
import jha.stopcovid.kafka.services.KafkaProducer;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/locations")
public class GeolocationController {

    private final KafkaProducer kafkaProducer;

    public GeolocationController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }
    @PostMapping
    public void sendLocalisationToTopic(@RequestBody GeolocationData data) {
        this.kafkaProducer.sendInformation(data, "topic-geoloc");
    }
}
