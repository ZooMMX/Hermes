package hello.controllers;

import hello.CandidatoRepository;
import hello.PuestoRepository;
import hello.UserRepository;
import hello.entities.Candidato;
import hello.entities.Puesto;
import hello.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by paumedina on 26/04/15.
 */
@Controller
public class CandidatosController {
    @Autowired
    CandidatoRepository candidatoRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PuestoRepository puestoRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/candidatos")
    private String verCandidatos(Model model) {

        List<Candidato> candidatos = (List<Candidato>) candidatoRepository.findAll();
        model.addAttribute("selectedMenu", "candidatos");
        model.addAttribute("candidatos", candidatos);
        model.addAttribute("noCandidatos", candidatos.size());

        return "vacante/candidatos";
    }

    //Muestra la página para asignar vacante a candi
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/candidatos/asignar")
    private String verFormularioAsignacion(Model model) {

        model.addAttribute("selectedMenu", "candidatos");
        List<User> usuarios = userRepository.findActiveUsers();
        model.addAttribute("usuarios", usuarios);
        Candidato candidato = new Candidato();
        model.addAttribute("candidato", candidato);
        List<Puesto> puestos = puestoRepository.findAll();
        model.addAttribute("puestos", puestos);
        model.addAttribute("readonly", false);

        return "vacante/candidato";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/candidatos/asignar", method = RequestMethod.POST)
    private String asignarCandidato(
            @ModelAttribute Candidato candidato,
            @RequestParam("puesto_id") Long puesto_id,
            Model model) {

        User user = userRepository.findByUsername(candidato.getUsername());
        Puesto puesto = puestoRepository.findOne(puesto_id);

        List<Puesto> puestos = puestoRepository.findAll();
        model.addAttribute("puestos", puestos);

        user.setCandidato(candidato);
        candidato.setUser(user);
        candidato.setPuesto(puesto);

        userRepository.save(user);
        //candidato.setUser(user);
        //candidatoRepository.save(candidato);

        return "redirect:/candidatos";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/candidatos/ver/{username}")
    private String verCandidato(Model m, @PathVariable String username, @RequestParam(value = "readonly", defaultValue = "true") Boolean readonly) {

        Candidato c = candidatoRepository.findOne(username);

        List<Puesto> puestos = puestoRepository.findAll();
        m.addAttribute("puestos", puestos);
        m.addAttribute("candidato", c);
        List<User> usuarios = userRepository.findActiveUsers();
        m.addAttribute("usuarios", usuarios);
        m.addAttribute("readonly", readonly);

        return "vacante/candidato";
    }
}
