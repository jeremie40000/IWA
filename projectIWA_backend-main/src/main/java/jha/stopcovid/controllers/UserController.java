package jha.stopcovid.controllers;

import jha.stopcovid.services.ContactService;
import jha.stopcovid.services.UserService;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

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
    public void isPositive(@PathVariable @Length(min = 36,max = 36) String idUser){
        System.out.println("ici");
        //userService.changeState(idUser);
    }

}
