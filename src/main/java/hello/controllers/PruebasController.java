package hello.controllers;

import hello.MediUser;
import hello.PruebaRepository;
import hello.UserRepository;
import hello.entities.Prueba;
import hello.entities.User;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.*;

/**
 * Proyecto Hermes HR
 * User: paumedina
 * Date: 04/12/14
 * Time: 13:52
 */
@Controller
public class PruebasController {
    @Autowired
    PruebaRepository pruebaRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager em;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/pruebas")
    public String pruebas(Model model) {

        List pruebas = (List) pruebaRepository.findAll();

        model.addAttribute("selectedMenu", "pruebas");
        model.addAttribute("pruebas", pruebas);
        //model.addAttribute("noUsuarios", usuarios.size());
        return "prueba/pruebas";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/pruebas", method = RequestMethod.POST)
    public String pruebasAdd(
            @ModelAttribute Prueba prueba,
            @RequestParam("file") MultipartFile[] file,
            Model model,
            Principal principal) throws IOException, SQLException {

        /* Gather information about user */
        MediUser activeUser = (MediUser) ((Authentication) principal).getPrincipal();
        User user = userRepository.findOne(activeUser.getUsername());

        /* Bytes to blob y guardado del documento adjunto */
        if(file.length > 0) {
            Blob blob = new javax.sql.rowset.serial.SerialBlob(file[0].getBytes());
            prueba.setDocumento(blob);
        }

        /* Guardar registro */
        prueba.setFecha(new Date());
        prueba.setAutor(user);
        pruebaRepository.save(prueba);

        return "redirect:/pruebas/?successfulRegister=true";

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/prueba/{id}")
    public String prueba(@PathVariable Long id, @RequestParam(value = "readonly", defaultValue = "true") Boolean readonly, Model model) {
        Prueba prueba = pruebaRepository.findOne(id);
        model.addAttribute("selectedMenu", "pruebas");
        model.addAttribute("readonly", readonly);
        model.addAttribute("prueba", prueba);
        //model.addAttribute("noUsuarios", usuarios.size());
        return "prueba/prueba";
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/prueba/documento/{id}.pdf")
    public String viewDocumento(@PathVariable("id")
                       Long id, HttpServletResponse response) {

        Prueba prueba = pruebaRepository.findOne(id);
        try {
            response.setHeader("Content-Disposition", "inline; filename=\"" +prueba.getNombre()+ ".pdf\"");
            response.setContentType("application/pdf");
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setContentLength((int) prueba.getDocumento().length());
            OutputStream out = response.getOutputStream();
            IOUtils.copy(prueba.getDocumento().getBinaryStream(), out);
            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

}
