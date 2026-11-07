package com.tecsup.petclinic.webs;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vet-specialties")
public class VetSpecialtyController {
    private final JdbcTemplate jdbc;
    public VetSpecialtyController(JdbcTemplate jdbc){this.jdbc=jdbc;}
    @PostMapping public ResponseEntity<?> assign(@RequestBody Map<String,Long> body){
        Long vet=body.get("vetId"), specialty=body.get("specialtyId"); if(vet==null||specialty==null)return ResponseEntity.badRequest().build();
        try{jdbc.update("INSERT INTO vet_specialties(vet_id,specialty_id) VALUES(?,?)",vet,specialty);return ResponseEntity.status(HttpStatus.CREATED).build();}
        catch(org.springframework.dao.DuplicateKeyException e){return ResponseEntity.status(HttpStatus.CONFLICT).build();}
    }
    @GetMapping("/vet/{vetId}") public List<Map<String,Object>> byVet(@PathVariable long vetId){return jdbc.queryForList("SELECT * FROM vet_specialties WHERE vet_id=?",vetId);}
    @GetMapping("/specialty/{specialtyId}") public List<Map<String,Object>> bySpecialty(@PathVariable long specialtyId){return jdbc.queryForList("SELECT * FROM vet_specialties WHERE specialty_id=?",specialtyId);}
    @DeleteMapping("/{vetId}/{specialtyId}") public ResponseEntity<?> delete(@PathVariable long vetId,@PathVariable long specialtyId){return jdbc.update("DELETE FROM vet_specialties WHERE vet_id=? AND specialty_id=?",vetId,specialtyId)==0?ResponseEntity.notFound().build():ResponseEntity.noContent().build();}
}
