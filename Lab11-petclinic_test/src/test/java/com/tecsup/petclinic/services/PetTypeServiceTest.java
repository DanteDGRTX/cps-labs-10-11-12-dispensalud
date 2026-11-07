package com.tecsup.petclinic.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

class PetTypeServiceTest extends JdbcTestSupport {
    @Test void testCreateType() { jdbc.update("INSERT INTO types(id,name) VALUES(101,'Gato')"); assertThat(jdbc.queryForObject("SELECT name FROM types WHERE id=101", String.class), is("Gato")); }
    @Test void testUpdateType() { jdbc.update("INSERT INTO types(id,name) VALUES(102,'Ave')"); jdbc.update("UPDATE types SET name='Ave domestica' WHERE id=102"); assertThat(jdbc.queryForObject("SELECT name FROM types WHERE id=102", String.class), is("Ave domestica")); }
    @Test void testFindTypeById() { assertThat(jdbc.queryForObject("SELECT name FROM types WHERE id=1", String.class), is("Perro")); }
    @Test void testDeleteType() { jdbc.update("INSERT INTO types(id,name) VALUES(104,'Temporal')"); jdbc.update("DELETE FROM types WHERE id=104"); assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM types WHERE id=104", Integer.class), is(0)); }
}
