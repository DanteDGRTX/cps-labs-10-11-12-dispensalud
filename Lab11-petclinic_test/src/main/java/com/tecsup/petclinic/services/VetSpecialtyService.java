package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.entities.Vet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VetSpecialtyService {
    private final JdbcTemplate jdbc;
    private final RowMapper<Specialty> specialtyMapper = (rs, row) -> new Specialty(rs.getLong("id"), rs.getString("name"),
            rs.getString("office"), (Integer) rs.getObject("h_open"), (Integer) rs.getObject("h_close"));
    private final RowMapper<Vet> vetMapper = (rs, row) -> new Vet(rs.getLong("id"), rs.getString("first_name"),
            rs.getString("last_name"), rs.getString("email"), rs.getString("phone"), rs.getBoolean("active"));
    public VetSpecialtyService(JdbcTemplate jdbc) { this.jdbc = jdbc; }
    public void assignSpecialty(Long vetId, Long specialtyId) {
        Integer existing = jdbc.queryForObject("SELECT COUNT(*) FROM vet_specialties WHERE vet_id=? AND specialty_id=?",
                Integer.class, vetId, specialtyId);
        if (existing == null || existing == 0) {
            jdbc.update("INSERT INTO vet_specialties(vet_id, specialty_id) VALUES (?, ?)", vetId, specialtyId);
        }
    }
    public List<Specialty> findSpecialtiesByVetId(Long vetId) {
        return jdbc.query("SELECT s.id, s.name, s.office, s.h_open, s.h_close FROM specialties s " +
                "JOIN vet_specialties vs ON vs.specialty_id=s.id WHERE vs.vet_id=? ORDER BY s.id", specialtyMapper, vetId);
    }
    public List<Vet> findVetsBySpecialtyId(Long specialtyId) {
        return jdbc.query("SELECT v.id, v.first_name, v.last_name, v.email, v.phone, v.active FROM vets v " +
                "JOIN vet_specialties vs ON vs.vet_id=v.id WHERE vs.specialty_id=? ORDER BY v.id", vetMapper, specialtyId);
    }
    public void removeSpecialtyFromVet(Long vetId, Long specialtyId) {
        jdbc.update("DELETE FROM vet_specialties WHERE vet_id=? AND specialty_id=?", vetId, specialtyId);
    }
}
