package jha.stopcovid.user.repositories;

import jha.stopcovid.user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from user_entity u where u.Id =:idUser")
    User getUserById(String idUser);

}
