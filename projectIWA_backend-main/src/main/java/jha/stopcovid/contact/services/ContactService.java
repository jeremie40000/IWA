package jha.stopcovid.contact.services;

import jha.stopcovid.contact.repositories.ContactRepository;
import jha.stopcovid.user.repositories.UserRepository;
import jha.stopcovid.contact.models.Contact;
import jha.stopcovid.contact.business.ContactData;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepository;
    @Autowired
    UserRepository userRepository;

    public final static long MILLIS_FOR_10_DAY = 10 * 24 * 60 * 60 * 1000L;


    public void addContactToDB(ContactData contact) {
        Contact contactUpdate = contactRepository.contactExist(contact.getIdUser1(), contact.getIdUser2());
        if (contactUpdate != null) {
            contactUpdate.setContacted_on(contact.getTimestamp());
            contactRepository.saveAndFlush(contactUpdate);
        } else {
            Contact contactModel = new Contact(contact.getIdUser1(), contact.getIdUser2(),contact.getTimestamp());
            contactRepository.saveAndFlush(contactModel);
        }
    }

    public void clearSafeContacts(Timestamp timestamp) {
        var contacts = contactRepository.findAll();
        for (int el = 0; el<contacts.size(); el++) {
            if (dateExpired(timestamp, new Timestamp(Long.parseLong(contacts.get(el).getContacted_on()) * 1000)) == true) {
                contactRepository.delete(contacts.get(0));
            }
        }
    }

    private boolean dateExpired(Timestamp t1, Timestamp t2) {
        return Math.abs(t1.getTime() - t2.getTime()) > MILLIS_FOR_10_DAY;
    }


}