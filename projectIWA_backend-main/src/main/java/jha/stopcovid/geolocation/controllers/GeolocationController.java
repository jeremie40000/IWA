package jha.stopcovid.geolocation.controllers;

import jha.stopcovid.kafka.services.KafkaProducer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class GeolocationController {

    private KafkaProducer kafkaProducer;

    @GetMapping("/location")
    public void userLocation(@RequestBody Object data) {
        if(data == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
        }
        kafkaProducer.sendInformation(data, "topic-geoloc");


    }
}
