package com.tecsup.petclinic.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.sql.Date;
import org.junit.jupiter.api.Test;

class VisitServiceTest extends JdbcTestSupport {
    @Test void testCreateVisit() { jdbc.update("INSERT INTO visits(id,pet_id,visit_date,description) VALUES(101,1,?,?)", Date.valueOf("2026-11-07"), "Control"); assertThat(jdbc.queryForObject("SELECT description FROM visits WHERE id=101", String.class), is("Control")); }
    @Test void testUpdateVisit() { jdbc.update("INSERT INTO visits(id,pet_id,visit_date,description) VALUES(102,1,?,?)", Date.valueOf("2026-11-07"), "Inicial"); jdbc.update("UPDATE visits SET description='Seguimiento' WHERE id=102"); assertThat(jdbc.queryForObject("SELECT description FROM visits WHERE id=102", String.class), is("Seguimiento")); }
    @Test void testFindVisitsByPetId() { jdbc.update("INSERT INTO visits(id,pet_id,visit_date,description) VALUES(103,1,?,?)", Date.valueOf("2026-11-07"), "Consulta"); assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM visits WHERE pet_id=1", Integer.class), is(1)); }
    @Test void testDeleteVisit() { jdbc.update("INSERT INTO visits(id,pet_id,visit_date,description) VALUES(104,1,?,?)", Date.valueOf("2026-11-07"), "Temporal"); jdbc.update("DELETE FROM visits WHERE id=104"); assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM visits WHERE id=104", Integer.class), is(0)); }
}
