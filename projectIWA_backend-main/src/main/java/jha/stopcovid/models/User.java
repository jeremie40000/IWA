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

    public User() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIs_infected() {
        return is_infected;
    }

    public void setIs_infected(Boolean is_infected) {
        this.is_infected = is_infected;
    }

    public String getInfection_date() {
        return infection_date;
    }

    public void setInfection_date(String infection_date) {
        this.infection_date = infection_date;
    }
}
