package com.example.sprigbootv2.Controladores;

import com.example.sprigbootv2.Repositorios.CrucerosRepositorio;
import com.example.sprigbootv2.Repositorios.NavieraRepositorio;
import com.example.sprigbootv2.Repositorios.PuertoRepositorio;
import com.example.sprigbootv2.modelos.Cruceros;
import com.example.sprigbootv2.modelos.Navieras;
import com.example.sprigbootv2.modelos.Puertos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api2")
public class ApiController2 {

    private static final Logger logger = LoggerFactory.getLogger(ApiController2.class);

    @Autowired
    CrucerosRepositorio crucerosRepositorio;

    @Autowired
    NavieraRepositorio navierasRepositorio;

    @Autowired
    PuertoRepositorio PuertoRepositorio;



    /**
     * Obtiene todos los cruceros almacenados en la base de datos.
     *
     * @return Lista de todos los cruceros disponibles.
     */
    @GetMapping("/cruceros")
    public List<Cruceros> all() {
        return crucerosRepositorio.findAll();
    }

    /**
     * Obtiene un crucero específico por su identificador único (ID).
     *
     * @param id El ID del crucero a buscar.
     * @return Una respuesta con el crucero si se encuentra, o un estado 404 si no existe.
     */
    @GetMapping("/cruceros/{id}")
    public ResponseEntity<Cruceros> getCruceroById(@PathVariable String id) {
        return crucerosRepositorio.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Obtiene todos los cruceros pertenecientes a una naviera específica.
     *
     * @param nombreNaviera El nombre de la naviera cuyos cruceros se desean buscar.
     * @return Una lista de cruceros si existen, o un mensaje de error si no se encuentran.
     */
    @GetMapping("/cruceros/naviera/{nombreNaviera}")
    public ResponseEntity<?> getCrucerosByNaviera(@PathVariable String nombreNaviera) {
        Optional<Navieras> naviera = navierasRepositorio.findByNombre(nombreNaviera);

        if (naviera.isPresent()) {
            List<Cruceros> cruceros = crucerosRepositorio.findByNaviera(naviera.get().get_id());

            if (!cruceros.isEmpty()) {
                return ResponseEntity.ok(cruceros);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron cruceros para esta naviera.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Naviera no encontrada.");
        }
    }

    /**
     * Obtiene todos los cruceros ordenados por puntuación (de mayor a menor).
     *
     * @return Lista de cruceros ordenados por puntuación en orden descendente.
     */
    @GetMapping("/cruceros/orden/puntuacion")
    public ResponseEntity<List<Cruceros>> getCrucerosByPuntuacion() {
        List<Cruceros> cruceros = crucerosRepositorio.findAllByOrderByPuntuacionDesc();

        if (!cruceros.isEmpty()) {
            return ResponseEntity.ok(cruceros);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Obtiene los 5 mejores cruceros según su puntuación.
     *
     * @return Lista con los 5 cruceros mejor puntuados.
     */
    @GetMapping("/cruceros/top5")
    public ResponseEntity<List<Cruceros>> getTop5Cruceros() {
        List<Cruceros> cruceros = crucerosRepositorio.findTop5ByOrderByPuntuacionDesc();

        if (!cruceros.isEmpty()) {
            return ResponseEntity.ok(cruceros);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    /**
     * Obtiene el ID de un puerto por su nombre.
     *
     * @param nombrePuerto Nombre del puerto.
     * @return ID del puerto si existe.
     */
    @GetMapping("/puertos/{nombrePuerto}")
    public ResponseEntity<?> getPuertoIdByNombre(@PathVariable String nombrePuerto) {
        Optional<Puertos> puerto = PuertoRepositorio.findByNombre(nombrePuerto);
        return puerto.map(value -> ResponseEntity.ok(value.get_id()))
                .orElseGet(() -> ResponseEntity.status(404).body("Puerto no encontrado"));
    }

    /**
     * Obtiene los cruceros que salen o llegan a un puerto por su nombre.
     *
     * @param nombrePuerto Nombre del puerto.
     * @return Lista de cruceros que pasan por ese puerto.
     */
    /**
     * Obtiene los cruceros que **salen** de un puerto por su nombre.
     *
     * @param nombrePuerto Nombre del puerto.
     * @return Lista de cruceros que **salen** de ese puerto.
     */
    @GetMapping("/cruceros/salida/{nombrePuerto}")
    public ResponseEntity<?> getCrucerosByPuertoSalida(@PathVariable String nombrePuerto) {
        Optional<Puertos> puerto = PuertoRepositorio.findByNombre(nombrePuerto);

        if (puerto.isPresent()) {
            String puertoId = puerto.get().get_id();
            List<Cruceros> crucerosSalida = crucerosRepositorio.findByPuertoSalida(puertoId);

            if (crucerosSalida.isEmpty()) {
                return ResponseEntity.status(404).body("No se encontraron cruceros que salgan de este puerto.");
            }

            return ResponseEntity.ok(crucerosSalida);
        } else {
            return ResponseEntity.status(404).body("Puerto no encontrado.");
        }
    }

    /**
     * Obtiene los cruceros que **llegan** a un puerto por su nombre.
     *
     * @param nombrePuerto Nombre del puerto.
     * @return Lista de cruceros que **llegan** a ese puerto.
     */
    @GetMapping("/cruceros/llegada/{nombrePuerto}")
    public ResponseEntity<?> getCrucerosByPuertoLlegada(@PathVariable String nombrePuerto) {
        Optional<Puertos> puerto = PuertoRepositorio.findByNombre(nombrePuerto);

        if (puerto.isPresent()) {
            String puertoId = puerto.get().get_id();
            List<Cruceros> crucerosLlegada = crucerosRepositorio.findByPuertoLlegada(puertoId);

            if (crucerosLlegada.isEmpty()) {
                return ResponseEntity.status(404).body("No se encontraron cruceros que lleguen a este puerto.");
            }

            return ResponseEntity.ok(crucerosLlegada);
        } else {
            return ResponseEntity.status(404).body("Puerto no encontrado.");
        }
    }


    /**
     * Obtiene los cruceros que tienen una ruta específica de salida y llegada.
     *
     * @param puertoSalida Nombre del puerto de salida.
     * @param puertoLlegada Nombre del puerto de llegada.
     * @return Una lista de cruceros que cumplen con la ruta especificada.
     */
    @GetMapping("/cruceros/ruta")
    public ResponseEntity<?> getCrucerosByRuta(@RequestParam String puertoSalida, @RequestParam String puertoLlegada) {
        // Buscar los IDs de los puertos
        Optional<Puertos> puertoSalidaObj = PuertoRepositorio.findByNombre(puertoSalida);
        Optional<Puertos> puertoLlegadaObj = PuertoRepositorio.findByNombre(puertoLlegada);

        if (puertoSalidaObj.isEmpty() || puertoLlegadaObj.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Uno o ambos puertos no existen.");
        }

        String idPuertoSalida = puertoSalidaObj.get().get_id();
        String idPuertoLlegada = puertoLlegadaObj.get().get_id();


        List<Cruceros> cruceros = crucerosRepositorio.findByRutasPuertoSalidaAndPuertoLlegada(idPuertoSalida, idPuertoLlegada);

        if (cruceros.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron cruceros para esta ruta.");
        }

        return ResponseEntity.ok(cruceros);
    }

    @Value("${admin.password}")
    private String adminPassword;
    @PostMapping("/cruceros/create")
    public ResponseEntity<?> crearCrucero(@RequestBody Cruceros crucero, @RequestParam String password) {

        if (!password.equals(adminPassword)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acceso denegado: contraseña incorrecta.");
        }

        Cruceros nuevoCrucero = crucerosRepositorio.save(crucero);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCrucero);
    }







}
