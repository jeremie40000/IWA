package jha.stopcovid.models;

import javax.persistence.Entity;
import  javax.persistence.Id;

@Entity(name = "user_entity")
public class User {

    @Id
    private String Id;

    private String email;

    private Boolean is_infected;

    private String infection_date;
}
