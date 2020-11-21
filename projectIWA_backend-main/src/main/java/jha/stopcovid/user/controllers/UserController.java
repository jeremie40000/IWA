package jha.stopcovid.user.controllers;

import jha.stopcovid.contact.services.ContactService;
import jha.stopcovid.user.services.UserService;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Validated
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ContactService contactService;

    @PutMapping("/{idUser}")
    public void isPositive(@PathVariable @Length(min = 36,max = 36, message = "id not valid") String idUser){
        userService.changeState(idUser);
    }

}
