package org.iesvdm.pruebarecuud3.dao;

import org.iesvdm.pruebarecuud3.domain.Departamento;

import java.util.List;
import java.util.Optional;

public interface DepartamentoDAO {
    public void create(Departamento departamento);
    public List<Departamento> getAll();
    public Optional<Departamento> find(int id);

    public void update(Departamento departamento);

    public void delete(int id);
}