package jha.stopcovid.user.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import  javax.persistence.Id;

@Entity(name = "users")
public class User {

    @Id
    private String Id;

    private String email;

    private String password;
}
