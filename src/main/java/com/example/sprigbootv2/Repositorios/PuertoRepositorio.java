package com.example.sprigbootv2.Repositorios;


import com.example.sprigbootv2.modelos.Puertos;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface PuertoRepositorio extends MongoRepository<Puertos, String> {
    Optional<Puertos> findByNombre(String nombre);
}

