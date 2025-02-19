package com.example.sprigbootv2.Repositorios;


import com.example.sprigbootv2.modelos.Cruceros;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface CrucerosRepositorio extends MongoRepository<Cruceros, String> {
    List<Cruceros> findByNaviera(String naviera);
    List<Cruceros> findAllByOrderByPuntuacionDesc();
    List<Cruceros> findTop5ByOrderByPuntuacionDesc();
    @Query("{ 'rutas.puerto_salida': ?0 }")
    List<Cruceros> findByPuertoSalida(String puertoId);

    @Query("{ 'rutas.puerto_llegada': ?0 }")
    List<Cruceros> findByPuertoLlegada(String puertoId);

    @Query("{ 'rutas.puerto_salida': ?0, 'rutas.puerto_llegada': ?1 }")
    List<Cruceros> findByRutasPuertoSalidaAndPuertoLlegada(String puertoSalida, String puertoLlegada);




    @Query("{ $or: [ { 'rutas.fecha_salida': { $gte: ?0, $lte: ?1 } }, { 'rutas.fecha_llegada': { $gte: ?0, $lte: ?1 } } ] }")
    List<Cruceros> findByFechas(Date fechaInicio, Date fechaFin);

}

