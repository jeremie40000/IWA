package jha.stopcovid.services;

import jha.stopcovid.models.ContactData;
import jha.stopcovid.models.GeolocationData;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class KafkaConsumer {

    private LocalisationDataset localisationDataset = new LocalisationDataset();

    private final KafkaProducer kafkaProducer;

    public KafkaConsumer(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }


    @KafkaListener(topics="topic-geoloc", groupId ="my_group_id")
    public void getInformationGeolocation(GeolocationData geolocationData) {
        System.out.println(geolocationData.toString());
        long timestampNewData = new Long(geolocationData.getTimestamp());
        timestampNewData = (((timestampNewData / 60) * 60) / 300) * 300;
        System.out.println(timestampNewData);
        if(timestampNewData == localisationDataset.getTimestamp()){
            System.out.println("In equal");
            ArrayList<ContactData> contacts = localisationDataset.geolocationComparison(geolocationData);
            for (int element = 0; element < contacts.size(); element++) {
                System.out.println("contact : " + contacts.get(element).toString());
            }
            for (int element = 0; element < contacts.size(); element++) {
                kafkaProducer.sendInformation(contacts.get(element), "topic-suspicious");
            }
            localisationDataset.addValue(geolocationData);
        } else if (timestampNewData > localisationDataset.getTimestamp()){
            System.out.println("In superior");
            localisationDataset.setTimestamp(timestampNewData);
            localisationDataset.clearValues();
            localisationDataset.addValue(geolocationData);
        }
    }

    @KafkaListener(topics="topic-suspicious", groupId ="my_group_id")
    public void getInformationContact(ContactData info) {
        System.out.println(info.toString());
    }
}
