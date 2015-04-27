package hello.controllers;

import hello.CandidatoRepository;
import hello.PuestoRepository;
import hello.UserRepository;
import hello.entities.Candidato;
import hello.entities.Puesto;
import hello.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by octavioruiz on 26/04/15.
 */
@Controller
public class CandidatosController {
    @Autowired
    CandidatoRepository candidatoRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PuestoRepository puestoRepository;

    @RequestMapping(value = "/candidatos")
    private String verCandidatos(Model model) {

        List<Candidato> candidatos = (List<Candidato>) candidatoRepository.findAll();
        model.addAttribute("selectedMenu", "candidatos");
        model.addAttribute("candidatos", candidatos);

        return "vacante/candidatos";
    }

    @RequestMapping(value = "/candidatos/asignar")
    private String verFormularioAsignacion(Model model) {

        model.addAttribute("selectedMenu", "candidatos");
        Candidato candidato = new Candidato();

        model.addAttribute("candidato", candidato);

        return "vacante/candidato";
    }

    @RequestMapping(value = "/candidatos/asignar", method = RequestMethod.POST)
    private String asignarCandidato(
            @ModelAttribute Candidato candidato,
            @RequestParam("puesto_id") Long puesto_id,
            Model model) {

        User user = userRepository.findByUsername(candidato.getUsername());
        Puesto puesto = puestoRepository.findOne(puesto_id);

        user.setCandidato(candidato);
        candidato.setUser(user);
        candidato.setPuesto(puesto);

        userRepository.save(user);
        //candidato.setUser(user);
        //candidatoRepository.save(candidato);

        return "redirect:/candidatos";
    }

    @RequestMapping(value = "/candidatos/ver/{username}")
    private String verCandidato(Model m, @PathVariable String username) {

        Candidato c = candidatoRepository.findOne(username);

        m.addAttribute("candidato", c);

        return "vacante/candidato";
    }
}
