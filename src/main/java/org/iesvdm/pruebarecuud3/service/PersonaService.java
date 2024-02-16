package org.iesvdm.pruebarecuud3.service;

import org.iesvdm.pruebarecuud3.dao.DepartamentoDAOImpl;
import org.iesvdm.pruebarecuud3.dao.PersonaDAOImpl;
import org.iesvdm.pruebarecuud3.domain.Departamento;
import org.iesvdm.pruebarecuud3.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaService {

    @Autowired
    PersonaDAOImpl personaDAO;

    @Autowired
    DepartamentoDAOImpl departamentoDAO;
    
    public void createpersona (Persona persona) {
        personaDAO.create(persona);

    }


    public List<Persona> listAll () {
        return personaDAO.getAll();
    }

    public List<Departamento> listAllDepartamentos() {
        return departamentoDAO.getAll();
    }

//    Conteo de profesores totales.
    public long conteoProfesores (List<Persona> personas) {
        return personas.stream()
                .filter(persona -> persona.getTipo().equalsIgnoreCase("profesor"))
                .count();

    }
//    Conteo total de asignaturas de los catedráticos
    public long conteoAsignatCatedraticos () {
        return personaDAO.asignaTurasCatedraticos();
    }



}
