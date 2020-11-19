package jha.stopcovid.Repositories;

import jha.stopcovid.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {


    User getUserById(String idUser);

    @Query("SELECT u from user_entity u where u.Id =:idUser ")
    User findUserById(@Param("idUser") String idUser);

}
