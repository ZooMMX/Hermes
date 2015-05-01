package hello.controllers;

import hello.MediUser;
import hello.PruebaAsignadaRepository;
import hello.PruebaRepository;
import hello.UserRepository;
import hello.entities.Candidato;
import hello.entities.Prueba;
import hello.entities.PruebaAsignada;
import hello.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Date;
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

    @Autowired
    UserRepository userRepository;

    @Autowired
    PruebaAsignadaRepository pruebaAsignadaRepository;

    @PreAuthorize("hasRole('ROLE_CANDIDATO')")
    @RequestMapping("/tomarPruebas")
    public String tomarPrueba(Model model, Principal principal) {
        model.addAttribute("selectedMenu", "tomarPruebas");

        /* Gather information about user */
        MediUser activeUser = (MediUser) ((Authentication) principal).getPrincipal();
        User user = userRepository.findOne(activeUser.getUsername());
        Candidato candidato = user.getCandidato();

        List<Prueba> pruebas = (List<Prueba>) pruebaRepository.findPruebaCandidato(candidato.getUsername());
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

    @PreAuthorize("hasRole('ROLE_CANDIDATO')")
    @RequestMapping(value = "/tomarPruebas", method = RequestMethod.POST)
    public String contestarPost(
            @RequestParam String respuestas,
            @RequestParam(value = "prueba_id") Long pruebaId,
            Principal principal) {

        /* Gather information about user */
        MediUser activeUser = (MediUser) ((Authentication) principal).getPrincipal();
        User user = userRepository.findOne(activeUser.getUsername());
        Candidato candidato = user.getCandidato();

        /* Gather prueba */
        Prueba prueba = pruebaRepository.findOne(pruebaId);

        PruebaAsignada pruebaAsignada = new PruebaAsignada();
        pruebaAsignada.setRespuestas(respuestas);
        pruebaAsignada.setCandidato(candidato);
        pruebaAsignada.setPrueba(prueba);
        pruebaAsignada.setFechaEvaluacion(new Date());

        pruebaAsignadaRepository.save(pruebaAsignada);

        return "redirect:/tomarPruebas";
    }
}
