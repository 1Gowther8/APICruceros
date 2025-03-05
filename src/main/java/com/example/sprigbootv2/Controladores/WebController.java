package com.example.sprigbootv2.Controladores;

import com.example.sprigbootv2.Repositorios.CrucerosRepositorio;
import com.example.sprigbootv2.Repositorios.DestinoRepositorio;
import com.example.sprigbootv2.Repositorios.NavieraRepositorio;
import com.example.sprigbootv2.Repositorios.PuertoRepositorio;
import com.example.sprigbootv2.modelos.Cruceros;
import com.example.sprigbootv2.modelos.Navieras;
import com.example.sprigbootv2.modelos.Destinos;
import com.example.sprigbootv2.modelos.Puertos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/web")
public class WebController {

    @Autowired
    CrucerosRepositorio cruceroRepository;

    @Autowired
    NavieraRepositorio navieraRepository;

    @Autowired
    DestinoRepositorio destinosRepository;

    @Autowired
    PuertoRepositorio PuertoRepositorio;



    /**
     * Muestra una lista de cruceros ordenados por puntuación descendente.
     *
     * @param model El modelo para pasar datos a la vista.
     * @return La plantilla de cruceros por puntuación.
     */
    @GetMapping("/cruceros/puntuacion")
    public String mostrarCrucerosPuntuacion(Model model) {
        List<Cruceros> cruceros = cruceroRepository.findAllByOrderByPuntuacionDesc();

        List<Cruceros> crucerosModificados = cruceros.stream().peek(crucero -> {
            Optional<Navieras> naviera = navieraRepository.findById(crucero.getNaviera());
            naviera.ifPresent(n -> crucero.setNaviera(n.getNombre()));

        }).collect(Collectors.toList());

        model.addAttribute("cruceros", crucerosModificados);
        return "crucerospuntuacion";
    }


    /**
     * Muestra los detalles de un crucero específico.
     *
     * @param id    El ID del crucero.
     * @param model El modelo para pasar datos a la vista.
     * @return La plantilla de detalles del crucero si se encuentra, de lo contrario redirige a la página de puntuación de cruceros.
     */
    @GetMapping("/cruceros/{id}")
    public String mostrarDetalleCrucero(@PathVariable String id, Model model) {
        Optional<Cruceros> cruceroOpt = cruceroRepository.findById(id);

        if (cruceroOpt.isPresent()) {
            Cruceros crucero = cruceroOpt.get();

            Optional<Navieras> naviera = navieraRepository.findById(crucero.getNaviera());
            naviera.ifPresent(n -> crucero.setNaviera(n.getNombre()));

            crucero.getRutas().forEach(ruta -> {
                List<String> nombresDestinos = ruta.getDestinos().stream()
                        .map(destinoId -> destinosRepository.findById(destinoId)
                                .map(Destinos::getNombre)
                                .orElse("Destino Desconocido"))
                        .collect(Collectors.toList());
                ruta.setDestinos(nombresDestinos);

                if (ruta.getPuerto_salida() != null) {
                    Optional<Puertos> puertoSalida = PuertoRepositorio.findById(ruta.getPuerto_salida());
                    puertoSalida.ifPresent(puerto -> ruta.setPuerto_salida(puerto.getNombre()));
                }

                if (ruta.getPuerto_llegada() != null) {
                    Optional<Puertos> puertoLlegada = PuertoRepositorio.findById(ruta.getPuerto_llegada());
                    puertoLlegada.ifPresent(puerto -> ruta.setPuerto_llegada(puerto.getNombre()));
                }
            });

            model.addAttribute("crucero", crucero);
            return "crucerodetalle";
        } else {
            return "redirect:/web/cruceros/puntuacion";
        }
    }


}
