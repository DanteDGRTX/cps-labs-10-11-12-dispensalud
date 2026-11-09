package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {
    private final JdbcTemplate jdbc;
    private final RowMapper<Owner> mapper = (rs, row) -> new Owner(rs.getLong("id"), rs.getString("first_name"),
            rs.getString("last_name"), rs.getString("address"), rs.getString("city"), rs.getString("telephone"));
    public OwnerService(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    public Owner findById(Long id) {
        return jdbc.query("SELECT id, first_name, last_name, address, city, telephone FROM owners WHERE id=?", mapper, id)
                .stream().findFirst().orElseThrow(() -> new OwnerNotFoundException("Owner not found: " + id));
    }
    public List<Owner> findByLastName(String lastName) {
        return jdbc.query("SELECT id, first_name, last_name, address, city, telephone FROM owners WHERE last_name=?", mapper, lastName);
    }
    public Owner create(Owner owner) {
        Number id = new SimpleJdbcInsert(jdbc.getDataSource()).withTableName("owners").usingGeneratedKeyColumns("id").executeAndReturnKey(
                new MapSqlParameterSource().addValue("first_name", owner.getFirstName()).addValue("last_name", owner.getLastName())
                        .addValue("address", owner.getAddress()).addValue("city", owner.getCity()).addValue("telephone", owner.getTelephone()));
        return findById(id.longValue());
    }
    public Owner update(Owner owner) {
        jdbc.update("UPDATE owners SET first_name=?, last_name=?, address=?, city=?, telephone=? WHERE id=?",
                owner.getFirstName(), owner.getLastName(), owner.getAddress(), owner.getCity(), owner.getTelephone(), owner.getId());
        return findById(owner.getId());
    }
    public void delete(Long id) {
        if (jdbc.update("DELETE FROM owners WHERE id=?", id) == 0) throw new OwnerNotFoundException("Owner not found: " + id);
    }
}
