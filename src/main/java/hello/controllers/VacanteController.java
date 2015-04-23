package hello.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by octavioruiz on 22/04/15.
 */
public class VacanteController {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/vacantes")
    public String vacantes(Model model) {

        return "vacante/vacantes";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/vacante")
    public String vacante(Model model) {

        return "vacante/vacante";
    }
}
