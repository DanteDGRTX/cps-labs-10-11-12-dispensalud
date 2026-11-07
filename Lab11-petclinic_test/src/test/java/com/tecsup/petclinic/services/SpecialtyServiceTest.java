package com.tecsup.petclinic.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

class SpecialtyServiceTest extends JdbcTestSupport {
    @Test void testCreateSpecialty() { jdbc.update("INSERT INTO specialties(id,name) VALUES(101,'Oftalmologia')"); assertThat(jdbc.queryForObject("SELECT name FROM specialties WHERE id=101", String.class), is("Oftalmologia")); }
    @Test void testUpdateSpecialty() { jdbc.update("INSERT INTO specialties(id,name) VALUES(102,'General')"); jdbc.update("UPDATE specialties SET name='Cirugia' WHERE id=102"); assertThat(jdbc.queryForObject("SELECT name FROM specialties WHERE id=102", String.class), is("Cirugia")); }
    @Test void testFindSpecialtyById() { assertThat(jdbc.queryForObject("SELECT name FROM specialties WHERE id=1", String.class), is("Cardiología")); }
    @Test void testDeleteSpecialty() { jdbc.update("INSERT INTO specialties(id,name) VALUES(104,'Temporal')"); jdbc.update("DELETE FROM specialties WHERE id=104"); assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM specialties WHERE id=104", Integer.class), is(0)); }
}
