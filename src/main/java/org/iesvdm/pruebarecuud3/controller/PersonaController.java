package org.iesvdm.pruebarecuud3.controller;

import jakarta.validation.Valid;
import org.iesvdm.pruebarecuud3.domain.Departamento;
import org.iesvdm.pruebarecuud3.domain.Persona;
import org.iesvdm.pruebarecuud3.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PersonaController {

    @Autowired
    PersonaService personaService;

    @GetMapping("/personas")
    public String listar (Model model) {
        List<Persona> listAllPersonas = personaService.listAll();
        model.addAttribute("listaPersonas", listAllPersonas);

        long conteoProfes = personaService.conteoProfesores(listAllPersonas);
        model.addAttribute("conteoProfes", conteoProfes);

        long conteoAsignatCatedraticos = personaService.conteoAsignatCatedraticos();
        model.addAttribute("conteoAsigCat", conteoAsignatCatedraticos);

        return "personas";
    }

    @GetMapping("/personas/crear")
    public String crear (Model model) {
        Persona persona = new Persona();
        model.addAttribute("persona", persona);

        List<Departamento> departamentos = personaService.listAllDepartamentos();
        model.addAttribute("listaDepartamentos", departamentos);


        return "crear-persona";
    }

    @PostMapping("/personas/crear")
    public String submitCrear (@Valid @ModelAttribute("persona") Persona persona, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("persona", persona);

            List<Departamento> departamentos = personaService.listAllDepartamentos();
            model.addAttribute("listaDepartamentos", departamentos);

            return "crear-persona";
        }
        personaService.createpersona(persona);
        List<Persona> listaPersonas = personaService.listAll();
        model.addAttribute("listaPersonas", listaPersonas);

        return "/personas";
    }

}
