package jha.stopcovid.services;

import javassist.NotFoundException;
import jha.stopcovid.Repositories.ContactRepository;
import jha.stopcovid.Repositories.UserRepository;
import jha.stopcovid.models.Contact;
import jha.stopcovid.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public void changeState(String idUser) {
        User user = userRepository.findUserById(idUser);
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
        System.out.println("send to : "+userInContact.getEmail());
        emailService.sendMail(userInContact.getEmail(), contactedDate.toString());
    }


}
