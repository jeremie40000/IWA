package jha.stopcovid.services;

import javassist.NotFoundException;
import jha.stopcovid.Repositories.UserRepository;
import jha.stopcovid.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void isPositive(String idUser) {
        userRepository.putState(idUser);
    }


}
