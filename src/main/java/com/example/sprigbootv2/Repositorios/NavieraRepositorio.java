package com.example.sprigbootv2.Repositorios;


import com.example.sprigbootv2.modelos.Navieras;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface NavieraRepositorio extends MongoRepository<Navieras, String> {
    Optional<Navieras> findByNombre(String nombre);
}
