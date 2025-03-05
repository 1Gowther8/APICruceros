package com.example.sprigbootv2.Repositorios;

import com.example.sprigbootv2.modelos.Destinos;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinoRepositorio extends MongoRepository<Destinos, String> {
}
