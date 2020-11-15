package jha.stopcovid.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "contacts")
@Access(AccessType.FIELD)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Contact {

    @Id
    private Long id_contact;

    private String id_user1;

    private String id_user2;

    private String contacted_on;

    public Long getId_contact() {
        return id_contact;
    }

    public void setId_contact(Long id_contact) {
        this.id_contact = id_contact;
    }

    public String getId_user1() {
        return id_user1;
    }

    public void setId_user1(String id_user1) {
        this.id_user1 = id_user1;
    }

    public String getId_user2() {
        return id_user2;
    }

    public void setId_user2(String id_user2) {
        this.id_user2 = id_user2;
    }

    public String getContacted_on() {
        return contacted_on;
    }

    public void setContacted_on(String contacted_on) {
        this.contacted_on = contacted_on;
    }


}
