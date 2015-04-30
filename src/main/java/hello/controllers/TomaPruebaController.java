package hello.controllers;

import hello.PruebaRepository;
import hello.entities.Prueba;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Proyecto gs-securing-web
 * User: paumedina
 * Date: 29/04/15
 * Time: 9:54
 */
@Controller
public class TomaPruebaController {

    @Autowired
    PruebaRepository pruebaRepository;

    @PreAuthorize("hasRole('ROLE_CANDIDATO')")
    @RequestMapping("/tomarPruebas")
    public String tomarPrueba(Model model) {
        model.addAttribute("selectedMenu", "tomarPruebas");

        List<Prueba> pruebas = (List<Prueba>) pruebaRepository.findAll();
        model.addAttribute("pruebas", pruebas);

        return "prueba/tomar_prueba_layout";
    }

    @PreAuthorize("hasRole('ROLE_CANDIDATO')")
    @RequestMapping("/tomarPruebas/{id}")
    public String contestar(@PathVariable Long id, Model model) {
        model.addAttribute("selectedMenu", "tomarPruebas");

        Prueba prueba = pruebaRepository.findOne(id);

        model.addAttribute("prueba", prueba);

        return "prueba/tomar_prueba_paso_2";
    }
}
