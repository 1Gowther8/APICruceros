package com.example.sprigbootv2.modelos;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Puertos")
@Data
public class Puertos {
    @Id
    private String _id;
    private String nombre;
    private String pais;
    private String ciudad;
    private Coordenadas coordenadas;
    private String descripcion;

    @Data
    public static class Coordenadas {
        private Double lat;
        private Double lon;
    }
}
