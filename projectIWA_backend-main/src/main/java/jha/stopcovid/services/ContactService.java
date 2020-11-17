package jha.stopcovid.services;

import jha.stopcovid.Repositories.ContactRepository;
import jha.stopcovid.Repositories.UserRepository;
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
        var all = userRepository.findAll();
        System.out.println("test : "+contactRepository.findAllContacts(idUser));
    }
}