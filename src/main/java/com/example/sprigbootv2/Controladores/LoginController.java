package com.example.sprigbootv2.Controladores;


import com.example.sprigbootv2.Servicios.SecurityService;
import com.example.sprigbootv2.modelos.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    SecurityService securityService;

    @GetMapping
    public String login(Model model) {
        return "login";
    }


    /**
     * Procesa el inicio de sesión del usuario.
     *
     * @param session La sesión HTTP para almacenar atributos de sesión.
     * @param model   El modelo para pasar datos a la vista.
     * @param login   El objeto de usuario que contiene los datos de inicio de sesión.
     * @return La plantilla de redirección si el inicio de sesión es exitoso, de lo contrario, la plantilla de inicio de sesión.
     */
    @PostMapping
    public String processLogin(HttpSession session, Model model, @ModelAttribute User login) {
        var result = securityService.login(login.getUser(), login.getEmail());
        if(result.isPresent()){
            session.setAttribute("user", result.get());
            return "redirect:/web/cruceros/puntuacion";
        } else return "login";
    }

    /**
     * Procesa la salida del usuario.
     *
     * @param session La sesión HTTP para eliminar atributos de sesión.
     * @param model   El modelo para pasar datos a la vista.
     * @return La plantilla de redirección a la página principal.
     */
    @GetMapping("/exit")
    public String exit(HttpSession session, Model model) {
        session.removeAttribute("user");
        return "redirect:/web/";
    }

}
