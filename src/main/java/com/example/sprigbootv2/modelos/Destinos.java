package com.example.sprigbootv2.modelos;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Destinos")
@Data
public class Destinos {
    @Id
    private String _id;
    private String nombre;
    private String descripcion;
    private String imagen_url;
}
