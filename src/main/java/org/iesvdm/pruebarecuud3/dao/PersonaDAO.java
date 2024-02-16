package org.iesvdm.pruebarecuud3.dao;

import org.iesvdm.pruebarecuud3.domain.Persona;

import java.util.List;
import java.util.Optional;

public interface PersonaDAO {
    public void create(Persona persona);
    public List<Persona> getAll();
    public Optional<Persona> find(int id);

    public void update(Persona persona);

    public void delete(int id);
}
