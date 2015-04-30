package hello.controllers;

import hello.PruebaRepository;
import hello.PuestoRepository;
import hello.entities.Prueba;
import hello.entities.Puesto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by paumedina on 22/04/15.
 */
@Controller
public class VacanteController {

    @Autowired
    PuestoRepository puestoRepository;

    @Autowired
    PruebaRepository pruebaRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/vacantes")
    public String vacantes(Model model) {
        List<Puesto> puestos = puestoRepository.findAll();
        List<Prueba> pruebas = (List<Prueba>) pruebaRepository.findAll();

        model.addAttribute("selectedMenu", "vacantes");
        model.addAttribute("puestos", puestos);
        model.addAttribute("pruebas", pruebas);

        return "vacante/vacantes";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/vacante")
    public String vacante(Model model) {

        return "vacante/vacante";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/vacantes", method = RequestMethod.POST)
    public String vacantesAdd(
            @ModelAttribute Puesto puesto,
            Model model,
            @RequestParam(value = "pruebas_asignadas", required = false) List<Long> pruebasAsignadas,
            Principal principal) throws IOException, SQLException {

        /* Asignar pruebas */
        Set<Prueba> pruebaSet = new HashSet<>();
        if(pruebasAsignadas != null)
            for(Long pruebaId : pruebasAsignadas) {
                Prueba p = pruebaRepository.findOne(pruebaId);
                pruebaSet.add(p);
            }
        puesto.setPruebas(pruebaSet);

        /* Guardar registro */
        puestoRepository.save(puesto);

        return "redirect:/vacantes/?successfulRegister=true";

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/vacante/{id}")
    public String vacante(@PathVariable Long id, @RequestParam(value = "readonly", defaultValue = "true") Boolean readonly, Model model) {
        Puesto puesto = puestoRepository.findOne(id);
        List<Prueba> pruebas = (List<Prueba>) pruebaRepository.findAll();

        model.addAttribute("selectedMenu", "vacantes");
        model.addAttribute("readonly", readonly);
        model.addAttribute("puesto", puesto);
        model.addAttribute("pruebas", pruebas);
        model.addAttribute("pruebasAsignadas", puesto.getPruebas());

        return "vacante/vacante";
    }
}
