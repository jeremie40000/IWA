package jha.stopcovid.services;

import jha.stopcovid.Repositories.ContactRepository;
import jha.stopcovid.Repositories.UserRepository;
import jha.stopcovid.models.Contact;
import jha.stopcovid.models.ContactData;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepository;
    @Autowired
    UserRepository userRepository;

    public final static long MILLIS_FOR_10_DAY = 10 * 24 * 60 * 60 * 1000L;

    public void findContacts(String idUser) {
        contactRepository.findAllContacts(idUser);
        var all = contactRepository.findAll();
    }

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
        if (contacts.size() == 0) {
            throw new IllegalStateException("List is empty");
        }
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