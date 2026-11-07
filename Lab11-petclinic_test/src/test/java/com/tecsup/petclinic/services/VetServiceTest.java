package com.tecsup.petclinic.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class VetServiceTest extends JdbcTestSupport {
    private static final Logger log = LoggerFactory.getLogger(VetServiceTest.class);
    @Test void testCreateVet() { jdbc.update("INSERT INTO vets(id,first_name,last_name,email) VALUES(101,'Ana','Rios','ana@test')"); assertThat(rows("vets"), greaterThan(1)); }
    @Test void testUpdateVet() { jdbc.update("INSERT INTO vets(id,first_name,last_name,email) VALUES(102,'Ana','Rios','ana@test')"); jdbc.update("UPDATE vets SET last_name='Vega' WHERE id=102"); assertThat(jdbc.queryForObject("SELECT last_name FROM vets WHERE id=102", String.class), is("Vega")); }
    @Test void testFindVetByIdAndLastName() { jdbc.update("INSERT INTO vets(id,first_name,last_name,email) VALUES(103,'Eva','Soto','eva@test')"); assertThat(jdbc.queryForObject("SELECT first_name FROM vets WHERE id=103", String.class), is("Eva")); assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM vets WHERE last_name='Soto'", Integer.class), is(1)); log.info("Vet encontrado por id y apellido"); }
    @Test void testDeleteVet() { jdbc.update("INSERT INTO vets(id,first_name,last_name,email) VALUES(104,'Ian','Paz','ian@test')"); jdbc.update("DELETE FROM vets WHERE id=104"); assertThat(rows("vets"), greaterThan(0)); assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM vets WHERE id=104", Integer.class), is(0)); }
}
