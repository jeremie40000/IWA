package jha.stopcovid.Repositories;

import jha.stopcovid.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query(value = "SELECT c from contacts c where c.id_user1 =:idUser or c.id_user2 =:idUser")
    ArrayList<Contact> findAllContacts(@Param("idUser") String idUser);

}
