package jha.stopcovid.controllers;

import jha.stopcovid.services.ContactService;
import jha.stopcovid.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ContactService contactService;

    @PutMapping("/{idUser}")
    public void isPositive(@PathVariable String idUser){
        System.out.println("ici la putain de ta mere");
        userService.changeState(idUser);
    }

}
