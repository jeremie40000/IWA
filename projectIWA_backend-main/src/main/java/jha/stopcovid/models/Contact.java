package jha.stopcovid.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "contacts")
public class Contact {

    @Id
    private Long id_contact;

    private String id_user1;

    private String id_user2;

    private Date contacted_on;
}
