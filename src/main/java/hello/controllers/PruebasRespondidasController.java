package hello.controllers;


import hello.PruebaAsignadaRepository;
import hello.entities.PruebaAsignada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by paumedina on 01/05/15.
 */
@Controller
public class PruebasRespondidasController {
    @Autowired
    PruebaAsignadaRepository pruebaAsignadaRepository;

    @RequestMapping("/pruebasRespondidas")
    public String pruebasRespondidas(Model model) {

        List<PruebaAsignada> pruebaAsignadaList = (List<PruebaAsignada>) pruebaAsignadaRepository.findAll();
        model.addAttribute("pruebas", pruebaAsignadaList);

        model.addAttribute("selectedMenu", "respondidas");

        return "prueba/pruebas_respondidas";
    }
}
