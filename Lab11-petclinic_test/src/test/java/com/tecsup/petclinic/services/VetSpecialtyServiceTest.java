package com.tecsup.petclinic.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

class VetSpecialtyServiceTest extends JdbcTestSupport {
    @Test void testAssignSpecialtyToVet() { jdbc.update("INSERT INTO vet_specialties(vet_id,specialty_id) VALUES(1,1)"); assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM vet_specialties WHERE vet_id=1 AND specialty_id=1", Integer.class), is(1)); }
    @Test void testFindSpecialtiesByVetAndVetsBySpecialty() { jdbc.update("INSERT INTO vet_specialties(vet_id,specialty_id) VALUES(1,1)"); assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM vet_specialties WHERE vet_id=1", Integer.class), is(1)); assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM vet_specialties WHERE specialty_id=1", Integer.class), is(1)); }
    @Test void testDeleteVetSpecialtyRelation() { jdbc.update("INSERT INTO vet_specialties(vet_id,specialty_id) VALUES(1,1)"); jdbc.update("DELETE FROM vet_specialties WHERE vet_id=1 AND specialty_id=1"); assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM vet_specialties WHERE vet_id=1 AND specialty_id=1", Integer.class), is(0)); }
}
