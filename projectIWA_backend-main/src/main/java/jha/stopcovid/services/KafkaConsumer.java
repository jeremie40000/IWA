package jha.stopcovid.services;

import jha.stopcovid.models.ContactData;
import jha.stopcovid.models.GeolocationData;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    ContactService contactService;


    public KafkaConsumer(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }


    @KafkaListener(topics="topic-geoloc", groupId ="my_group_id")
    public void getInformationGeolocation(GeolocationData geolocationData) {
        //System.out.println(geolocationData.toString());
        long timestampNewData = new Long(geolocationData.getTimestamp());
        //timestampNewData = (((timestampNewData / 60) * 60) / 300) * 300;
        timestampNewData = ((timestampNewData / 60) * 60);
        //System.out.println(timestampNewData);
        if(timestampNewData == localisationDataset.getTimestamp()){
            ArrayList<ContactData> contacts = localisationDataset.geolocationComparison(geolocationData);
            for (int element = 0; element < contacts.size(); element++) {
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
        //System.out.println("info minute : " + c.get(Calendar.MINUTE));
        //System.out.println("LAST MINUTE CHECKED : " + suspiciousList.getLastMinuteChecked());
        if(c.get(Calendar.DAY_OF_MONTH) != dayOfMonth){
            contactsIdAddedToDB.clear();
            contactService.clearSafeContacts(ts);
            dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        }
        if(c.get(Calendar.MINUTE) != suspiciousList.getLastMinuteChecked()){
            System.out.println("SETTING LAST MINUTE CHECKED");
            suspiciousList.setContactList1(new ArrayList<ContactData>(suspiciousList.getContactList2()));
            suspiciousList.clearContactList2();
            suspiciousList.setLastMinuteChecked(c.get(Calendar.MINUTE));
        }
        System.out.println("info : "+info.toString());
        for(ContactData contact : suspiciousList.getContactList1()){
            System.out.println("contactFor : " + contact.toString());
            if(contact.getIdUser1().equals(info.getIdUser1()) && contact.getIdUser2().equals(info.getIdUser2())){
                if(!contactsIdAddedToDB.contains(info.getIdUser1()+info.getIdUser2())){
                    contactService.addContactToDB(info);
                    contactsIdAddedToDB.add(info.getIdUser1()+info.getIdUser2());
                } else {
                    System.out.println("CONTACTDATA ALREADY STORED IN DB");
                }
            }
        }
        this.suspiciousList.addToContactList2(info);
        //System.out.println("CONTACT ADDED");
        //System.out.println("suspiciousList0 :" + this.suspiciousList.getContactList1());
        //System.out.println("suspiciousList1 :" + this.suspiciousList.getContactList2());
    }
}
