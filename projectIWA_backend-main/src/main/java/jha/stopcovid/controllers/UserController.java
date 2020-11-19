package jha.stopcovid.controllers;

import jha.stopcovid.models.User;
import jha.stopcovid.services.ContactService;
import jha.stopcovid.services.EmailService;
import jha.stopcovid.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController("users")
public class UserController {

    @Autowired
    UserService userService;


    @Autowired
    ContactService contactService;


    @PutMapping("/{id}")
    public void isPositive(@PathVariable String idUser){
        userService.changeState(idUser);
    }

}
