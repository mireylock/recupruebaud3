package org.iesvdm.pruebarecuud3.dao;

import org.iesvdm.pruebarecuud3.domain.Departamento;
import org.iesvdm.pruebarecuud3.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class PersonaDAOImpl implements PersonaDAO{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Persona persona) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("""
                        INSERT INTO persona
                        (nombre, apellido1, apellido2, ciudad, direccion, telefono, fecha_nacimiento, tipo)
                        VALUE
                        (?, ?, ?, ?, ?, ?, ?, ?)
                        """, Statement.RETURN_GENERATED_KEYS);
            int idx = 1;
            ps.setString(idx++, persona.getNombre());
            ps.setString(idx++, persona.getApellido1());
            ps.setString(idx++, persona.getApellido2());
            ps.setString(idx++, persona.getCiudad());
            ps.setString(idx++, persona.getDireccion());
            ps.setString(idx++, persona.getTelefono());
            ps.setDate(idx++, Date.valueOf(persona.getFecha_nacimiento()));
            ps.setString(idx++, persona.getTipo());

            return ps;
        }, keyHolder);

        persona.setId(keyHolder.getKey().intValue());


    }

    @Override
    public List<Persona> getAll() {

        List<Persona> listPersona = this.jdbcTemplate.query("""
                SELECT * FROM  persona P left join profesor Pr on  Pr.id_profesor = P.id
                left join departamento D on Pr.id_departamento = D.id
                """, (rs, rowNum) -> new Persona (rs.getInt("P.id"),
                rs.getString("P.nif"),
                rs.getString("P.nombre"),
                rs.getString("P.apellido1"),
                rs.getString("P.apellido2"),
                rs.getString("P.ciudad"),
                rs.getString("P.direccion"),
                rs.getString("P.telefono"),
                rs.getDate("P.fecha_nacimiento").toLocalDate(),
                rs.getString("P.tipo"),
                new Departamento(rs.getInt("D.id"),
                        rs.getString("D.nombre")
                )
        ));

        return listPersona;
    }

    @Override
    public Optional<Persona> find(int id) {
        return Optional.empty();
    }

    @Override
    public void update(Persona persona) {

    }

    @Override
    public void delete(int id) {

    }

    public long asignaTurasCatedraticos() {
        String catedratico = "catedratico";

        return this.jdbcTemplate.queryForObject("""
                    SELECT count(*) from asignatura A join persona P on A.id_profesor=P.id WHERE P.tipo=?;
                """,
                long.class,
                catedratico);

    }
}
