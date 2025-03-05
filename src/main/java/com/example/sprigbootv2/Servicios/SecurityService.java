package com.example.sprigbootv2.Servicios;

import com.example.sprigbootv2.Repositorios.UserRepository;
import com.example.sprigbootv2.modelos.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityService {

    @Autowired
    private UserRepository userRepository;

    public Boolean requestValidation(String token){
        return userRepository.existsByToken(token);
    }

    public Optional<User> login(String user, String email){
        if(userRepository.existsByEmailAndUser(email, user)){
            return Optional.ofNullable( userRepository.findByEmail(email) );
        }
        else return Optional.empty();
    }
}
