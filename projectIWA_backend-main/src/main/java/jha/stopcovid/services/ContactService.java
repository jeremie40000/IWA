package jha.stopcovid.services;

import jha.stopcovid.Repositories.ContactRepository;
import jha.stopcovid.Repositories.UserRepository;
import jha.stopcovid.models.Contact;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepository;
    @Autowired
    UserRepository userRepository;

    public void findContacts(String idUser) {
        contactRepository.findAllContacts(idUser);
        var all = contactRepository.findAll();
        System.out.println(all.get(0).getId_user1());
        System.out.println("test : "+contactRepository.findAllContacts(idUser));
    }
}