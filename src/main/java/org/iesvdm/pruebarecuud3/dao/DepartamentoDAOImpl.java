package org.iesvdm.pruebarecuud3.dao;

import org.iesvdm.pruebarecuud3.domain.Departamento;
import org.iesvdm.pruebarecuud3.domain.Departamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DepartamentoDAOImpl implements DepartamentoDAO{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Departamento departamento) {

    }

    @Override
    public List<Departamento> getAll() {
        List<Departamento> listDepartamento = this.jdbcTemplate.query("""
                SELECT * FROM  departamento                
                """, (rs, rowNum) -> new Departamento(rs.getInt("id"),
                        rs.getString("nombre")
                )
        );

        return listDepartamento;
    }

    @Override
    public Optional<Departamento> find(int id) {
        return Optional.empty();
    }

    @Override
    public void update(Departamento departamento) {

    }

    @Override
    public void delete(int id) {

    }
}
