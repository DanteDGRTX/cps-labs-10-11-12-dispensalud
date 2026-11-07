package com.tecsup.petclinic.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class OwnerServiceTest extends JdbcTestSupport {
    private static final Logger log = LoggerFactory.getLogger(OwnerServiceTest.class);
    @Test void testCreateOwner() { jdbc.update("INSERT INTO owners(id,first_name,last_name,city) VALUES(101,'Rosa','Mamani','Lima')"); assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM owners WHERE id=101", Integer.class), is(1)); }
    @Test void testUpdateOwner() { jdbc.update("INSERT INTO owners(id,first_name,last_name,city) VALUES(102,'Rosa','Mamani','Lima')"); jdbc.update("UPDATE owners SET city='Cusco' WHERE id=102"); assertThat(jdbc.queryForObject("SELECT city FROM owners WHERE id=102", String.class), is("Cusco")); }
    @Test void testFindOwnerByIdNameAndCity() { assertThat(jdbc.queryForObject("SELECT first_name FROM owners WHERE id=1", String.class), is("Leo")); assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM owners WHERE city='Lima'", Integer.class), is(1)); log.info("Owner encontrado por id, nombre y ciudad"); }
    @Test void testDeleteOwner() { jdbc.update("INSERT INTO owners(id,first_name,last_name,city) VALUES(104,'Mia','Paz','Lima')"); jdbc.update("DELETE FROM owners WHERE id=104"); assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM owners WHERE id=104", Integer.class), is(0)); }
}
