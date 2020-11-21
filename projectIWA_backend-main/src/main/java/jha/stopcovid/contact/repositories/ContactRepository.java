package jha.stopcovid.contact.repositories;

import jha.stopcovid.contact.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query(value = "SELECT c from contacts c where c.id_user1 =:idUser or c.id_user2 =:idUser")
    ArrayList<Contact> findAllContacts(@Param("idUser") String idUser);

    @Query("SELECT c from contacts c where (c.id_user1=: idUser1 and c.id_user2=:idUser2) or (c.id_user1=:idUser2 and c.id_user2=:idUser1)")
    Contact contactExist(String idUser1, String idUser2);
}
