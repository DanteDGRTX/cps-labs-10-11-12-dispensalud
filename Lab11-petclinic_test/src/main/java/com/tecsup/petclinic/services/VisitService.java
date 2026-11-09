package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Visit;
import com.tecsup.petclinic.exception.VisitNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class VisitService {
    private final JdbcTemplate jdbc;
    private final RowMapper<Visit> mapper = (rs, row) -> new Visit(rs.getLong("id"), rs.getInt("pet_id"),
            rs.getDate("visit_date").toLocalDate(), rs.getString("description"));
    public VisitService(JdbcTemplate jdbc) { this.jdbc = jdbc; }
    public Visit findById(Long id) {
        return jdbc.query("SELECT id, pet_id, visit_date, description FROM visits WHERE id=?", mapper, id)
                .stream().findFirst().orElseThrow(() -> new VisitNotFoundException("Visit not found: " + id));
    }
    public List<Visit> findByPetId(Integer petId) {
        return jdbc.query("SELECT id, pet_id, visit_date, description FROM visits WHERE pet_id=? ORDER BY id", mapper, petId);
    }
    public Visit create(Visit visit) {
        Number id = new SimpleJdbcInsert(jdbc.getDataSource()).withTableName("visits").usingGeneratedKeyColumns("id")
                .executeAndReturnKey(new MapSqlParameterSource().addValue("pet_id", visit.getPetId())
                        .addValue("visit_date", Date.valueOf(visit.getVisitDate())).addValue("description", visit.getDescription()));
        return findById(id.longValue());
    }
    public Visit update(Visit visit) {
        jdbc.update("UPDATE visits SET pet_id=?, visit_date=?, description=? WHERE id=?", visit.getPetId(),
                Date.valueOf(visit.getVisitDate()), visit.getDescription(), visit.getId());
        return findById(visit.getId());
    }
    public void delete(Long id) {
        if (jdbc.update("DELETE FROM visits WHERE id=?", id) == 0) throw new VisitNotFoundException("Visit not found: " + id);
    }
}
