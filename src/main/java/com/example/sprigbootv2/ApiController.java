package com.example.sprigbootv2;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

  /*  @Autowired
    JuegoRepositorio juegoRepositorio;
    @Autowired UsuarioRepositorio usuarioRepositorio;

    @GetMapping("/juegos")
    public List<Juego> all() {
        return juegoRepositorio.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Juego> findById(@PathVariable String id) {
        if (juegoRepositorio.existsById(id)) {
            var juego = juegoRepositorio.findById(id).get();
            return (new ResponseEntity<Juego>(juego, HttpStatus.OK));
        } else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/genero/{genero}")
    public List<Juego> findByGenero(@PathVariable String genero) {
        return juegoRepositorio.findAllByGenero(genero);
    }

    @PostMapping("/")
    public ResponseEntity<Juego> create(@RequestBody Juego juego) { //requestbody manda json
        if (juegoRepositorio.existsById(juego.get_id()))
        {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        } else
        {
            juegoRepositorio.save(juego);
            return new ResponseEntity<>(juego, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete (@PathVariable String id, @RequestParam String token) {
        if (usuarioRepositorio.existsById(token)) {
            juegoRepositorio.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }*/


}
