package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exception.SpecialtyNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class SpecialtyService {
    private final JdbcTemplate jdbc;
    private final RowMapper<Specialty> mapper = (rs, row) -> new Specialty(rs.getLong("id"), rs.getString("name"),
            rs.getString("office"), (Integer) rs.getObject("h_open"), (Integer) rs.getObject("h_close"));
    public SpecialtyService(JdbcTemplate jdbc) { this.jdbc = jdbc; }
    public Specialty findById(Long id) {
        return jdbc.query("SELECT id, name, office, h_open, h_close FROM specialties WHERE id=?", mapper, id)
                .stream().findFirst().orElseThrow(() -> new SpecialtyNotFoundException("Specialty not found: " + id));
    }
    public Specialty create(Specialty specialty) {
        Number id = new SimpleJdbcInsert(jdbc.getDataSource()).withTableName("specialties").usingGeneratedKeyColumns("id")
                .executeAndReturnKey(new MapSqlParameterSource().addValue("name", specialty.getName()));
        return findById(id.longValue());
    }
    public Specialty update(Specialty specialty) {
        jdbc.update("UPDATE specialties SET name=? WHERE id=?", specialty.getName(), specialty.getId());
        return findById(specialty.getId());
    }
    public void delete(Long id) {
        if (jdbc.update("DELETE FROM specialties WHERE id=?", id) == 0) throw new SpecialtyNotFoundException("Specialty not found: " + id);
    }
}
