package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exception.VetNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VetService {
    private final JdbcTemplate jdbc;
    private final RowMapper<Vet> mapper = (rs, row) -> new Vet(rs.getLong("id"), rs.getString("first_name"),
            rs.getString("last_name"), rs.getString("email"), rs.getString("phone"), rs.getBoolean("active"));
    public VetService(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    public Vet findById(Long id) {
        return jdbc.query("SELECT id, first_name, last_name, email, phone, active FROM vets WHERE id=?", mapper, id)
                .stream().findFirst().orElseThrow(() -> new VetNotFoundException("Vet not found: " + id));
    }
    public List<Vet> findByLastName(String lastName) {
        return jdbc.query("SELECT id, first_name, last_name, email, phone, active FROM vets WHERE last_name=?", mapper, lastName);
    }
    public Vet create(Vet vet) {
        Number id = new SimpleJdbcInsert(jdbc.getDataSource()).withTableName("vets").usingGeneratedKeyColumns("id").executeAndReturnKey(
                new MapSqlParameterSource().addValue("first_name", vet.getFirstName()).addValue("last_name", vet.getLastName())
                        .addValue("email", vet.getEmail()).addValue("phone", vet.getPhone()).addValue("active", vet.isActive()));
        return findById(id.longValue());
    }
    public Vet update(Vet vet) {
        jdbc.update("UPDATE vets SET first_name=?, last_name=?, email=?, phone=?, active=? WHERE id=?",
                vet.getFirstName(), vet.getLastName(), vet.getEmail(), vet.getPhone(), vet.isActive(), vet.getId());
        return findById(vet.getId());
    }
    public void delete(Long id) {
        if (jdbc.update("DELETE FROM vets WHERE id=?", id) == 0) throw new VetNotFoundException("Vet not found: " + id);
    }
}
