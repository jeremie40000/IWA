package jha.stopcovid.user.services;

import jha.stopcovid.contact.repositories.ContactRepository;
import jha.stopcovid.user.repositories.UserRepository;
import jha.stopcovid.contact.models.Contact;
import jha.stopcovid.notification.services.EmailService;
import jha.stopcovid.user.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    EmailService emailService;

    public void changeState(String idUser) throws NullPointerException {
        User user = userRepository.getUserById(idUser);
        if(user == null){
            throw new NullPointerException("user does not exist");
        }
        if(user.getIs_infected().equals(true)) {
            throw new IllegalArgumentException("user is already positive");
        }
        user.setIs_infected(true);
        user.setInfection_date(new Timestamp(System.currentTimeMillis()).toString());
        userRepository.saveAndFlush(user);
        this.getContactedUsers(idUser);
    }

    public void getContactedUsers(String idUser) {
        ArrayList<Contact> contacts = contactRepository.findAllContacts(idUser);

        for (int element = 0; element<contacts.size();element++) {
            if (contacts.get(element).getId_user1().equals(idUser)) {
                this.sendEmailToUser(contacts.get(element).getId_user2(), contacts.get(element).getContacted_on());
            } else {
                this.sendEmailToUser(contacts.get(element).getId_user1(), contacts.get(element).getContacted_on());
            }
        }
    }

    public void sendEmailToUser(String idUser, String contactedOn) {
        Timestamp ts=new Timestamp(Long.parseLong(contactedOn) * 1000);
        Date contactedDate = new Date(ts.getTime());
        User userInContact = userRepository.getUserById(idUser);
        if ( userInContact == null) {
            throw new NullPointerException("user does not exist");
        }
        emailService.sendMail(userInContact.getEmail(), contactedDate.toString());
    }


}
