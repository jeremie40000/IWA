package jha.stopcovid.kafka.services;

import jha.stopcovid.contact.business.ContactData;
import jha.stopcovid.geolocation.models.GeolocationData;
import jha.stopcovid.contact.business.ContactDataset;
import jha.stopcovid.contact.services.ContactService;
import jha.stopcovid.geolocation.business.LocalisationDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Service
public class KafkaConsumer {

    private LocalisationDataset localisationDataset = new LocalisationDataset();
    private ContactDataset suspiciousList = new ContactDataset();
    private ArrayList<String> contactsIdAddedToDB = new ArrayList<String>();
    private int lastMinuteChecked = -1;
    private int dayOfMonth = -1;

    private final KafkaProducer kafkaProducer;

    @Value("${spring.kafka.producer.bootstrap-servers}")
    String serverAddress;

    @Autowired
    ContactService contactService;


    public KafkaConsumer(KafkaProducer kafkaProducer) {
        System.out.println("ADDRESS KAFKA : " + serverAddress);
        this.kafkaProducer = kafkaProducer;
    }


    @KafkaListener(topics="topic-geoloc", groupId ="my_group_id")
    public void getInformationGeolocation(GeolocationData geolocationData) {
        long timestampNewData = geolocationData.getTimestamp();
        timestampNewData = ((timestampNewData / 60) * 60);
        if(timestampNewData == localisationDataset.getTimestamp()){
            ArrayList<ContactData> contacts = localisationDataset.geolocationComparison(geolocationData);
            for (int element = 0; element < contacts.size(); element++) {
                System.out.println("CONTACT SENT TO TOPIC");
                kafkaProducer.sendInformation(contacts.get(element), "topic-suspicious");
            }
            localisationDataset.addValue(geolocationData);
        } else if (timestampNewData > localisationDataset.getTimestamp()){
            localisationDataset.setTimestamp(timestampNewData);
            localisationDataset.clearValues();
            localisationDataset.addValue(geolocationData);
        }
    }

    @KafkaListener(topics="topic-suspicious", groupId ="my_group_id")
    public void getInformationContact(ContactData info) {
        Timestamp ts=new Timestamp(Long.parseLong(info.getTimestamp()) * 1000);
        Date infoDate = new Date(ts.getTime());
        Calendar c = Calendar.getInstance();
        c.setTime(infoDate);
        if(c.get(Calendar.DAY_OF_MONTH) != dayOfMonth){
            contactsIdAddedToDB.clear();
            contactService.clearSafeContacts(ts);
            dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        }
        if(c.get(Calendar.MINUTE) != suspiciousList.getLastMinuteChecked()){
            suspiciousList.setContactList1(new ArrayList<ContactData>(suspiciousList.getContactList2()));
            suspiciousList.clearContactList2();
            suspiciousList.setLastMinuteChecked(c.get(Calendar.MINUTE));
        }
        for(ContactData contact : suspiciousList.getContactList1()){
            if(contact.getIdUser1().equals(info.getIdUser1()) && contact.getIdUser2().equals(info.getIdUser2())){
                if(!contactsIdAddedToDB.contains(info.getIdUser1()+info.getIdUser2())){
                    System.out.println("CONTACT SUSPICIOUS ADDED TO DB");
                    contactService.addContactToDB(info);
                    contactsIdAddedToDB.add(info.getIdUser1()+info.getIdUser2());
                }
            }
        }
        this.suspiciousList.addToContactList2(info);
    }
}
