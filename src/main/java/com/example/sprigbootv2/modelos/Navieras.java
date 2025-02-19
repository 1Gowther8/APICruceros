package com.example.sprigbootv2.modelos;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Navieras")
@Data
public class Navieras {
    @Id
    private String _id;
    private String nombre;
    private String pais_origen;
    private Integer fundacion;
    private String sitio_web;
    private String descripcion;
}
