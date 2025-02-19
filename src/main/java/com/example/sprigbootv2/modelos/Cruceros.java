package com.example.sprigbootv2.modelos;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "Cruceros")
@Data
public class Cruceros {
    @Id
    private String _id;
    private String nombre;
    private String naviera; // Ahora es un String en lugar de un ObjectId
    private Integer año_construccion;
    private Integer capacidad_pasajeros;
    private Integer capacidad_tripulacion;
    private Integer cubiertas;
    private Integer longitud;
    private Integer peso;
    private Double puntuacion;
    private Integer num_reviews;
    private String imagen_url;
    private List<Ruta> rutas;

    @Data
    public static class Ruta {
        private String puerto_salida;
        private String puerto_llegada;
        private List<String> destinos;
        private Date fecha_salida;
        private Date fecha_llegada;
        private Integer precio_base;
    }
}
