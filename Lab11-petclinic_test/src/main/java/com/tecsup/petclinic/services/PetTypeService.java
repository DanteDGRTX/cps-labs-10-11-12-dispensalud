package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.PetType;
import com.tecsup.petclinic.exception.PetTypeNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class PetTypeService {
    private final JdbcTemplate jdbc;
    private final RowMapper<PetType> mapper = (rs, row) -> new PetType(rs.getLong("id"), rs.getString("name"));
    public PetTypeService(JdbcTemplate jdbc) { this.jdbc = jdbc; }
    public PetType findById(Long id) {
        return jdbc.query("SELECT id, name FROM types WHERE id=?", mapper, id).stream().findFirst()
                .orElseThrow(() -> new PetTypeNotFoundException("Pet type not found: " + id));
    }
    public PetType create(PetType type) {
        Number id = new SimpleJdbcInsert(jdbc.getDataSource()).withTableName("types").usingGeneratedKeyColumns("id")
                .executeAndReturnKey(new MapSqlParameterSource().addValue("name", type.getName()));
        return findById(id.longValue());
    }
    public PetType update(PetType type) {
        jdbc.update("UPDATE types SET name=? WHERE id=?", type.getName(), type.getId());
        return findById(type.getId());
    }
    public void delete(Long id) {
        if (jdbc.update("DELETE FROM types WHERE id=?", id) == 0) throw new PetTypeNotFoundException("Pet type not found: " + id);
    }
}
