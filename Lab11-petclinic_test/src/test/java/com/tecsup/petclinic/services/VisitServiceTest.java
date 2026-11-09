package com.tecsup.petclinic.services;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.tecsup.petclinic.entities.Visit;
import com.tecsup.petclinic.exception.VisitNotFoundException;

@SpringBootTest
public class VisitServiceTest {
    @Autowired
    private VisitService visitService;

    @Test
    public void testFindVisitById() throws VisitNotFoundException {
        Visit visit = visitService.findById(1L);
        assertThat(visit.getPetId(), is(7));
    }

    @Test
    public void testFindVisitsByPetId() {
        List<Visit> visits = visitService.findByPetId(7);
        assertThat(visits.size(), is(2));
    }

    @Test
    public void testCreateVisit() {
        Visit visit = new Visit(7, LocalDate.of(2026, 11, 7), "Control de toma matutina 08:00 AM - Confirmada");
        Visit created = visitService.create(visit);
        assertThat(created.getId(), notNullValue());
        assertThat(created.getDescription(), is("Control de toma matutina 08:00 AM - Confirmada"));
    }

    @Test
    public void testUpdateVisit() {
        Visit created = visitService.create(new Visit(7, LocalDate.now(), "Toma programada 14:00"));
        created.setDescription("Toma Confirmada 14:02 - Paracetamol 500 mg");
        Visit updated = visitService.update(created);
        assertThat(updated.getDescription(), is("Toma Confirmada 14:02 - Paracetamol 500 mg"));
    }

    @Test
    public void testDeleteVisit() throws VisitNotFoundException {
        Visit created = visitService.create(new Visit(7, LocalDate.now(), "Visita temporal"));
        visitService.delete(created.getId());
        try {
            visitService.findById(created.getId());
            assertThat(true, is(false));
        } catch (VisitNotFoundException e) {
            assertThat(true, is(true));
        }
    }
}