package com.tecsup.petclinic.services;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exception.VetNotFoundException;

@SpringBootTest
public class VetServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(VetServiceTest.class);

    @Autowired
    private VetService vetService;

    @Test
    public void testFindVetById() throws VetNotFoundException {
        Vet vet = vetService.findById(1L);
        logger.info("[CASO 1] Vet encontrado: " + vet);
        assertThat(vet.getFirstName(), is("James"));
        assertThat(vet.getLastName(), is("Carter"));
    }

    @Test
    public void testFindVetByLastName() {
        List<Vet> vets = vetService.findByLastName("Leary");
        assertThat(vets.size(), is(1));
    }

    @Test
    public void testCreateVet() {
        Vet vet = new Vet("Ronal", "Gonzales");
        Vet created = vetService.create(vet);
        logger.info("[CASO 1] Vet creado: " + created);
        assertThat(created.getId(), notNullValue());
        assertThat(created.getFirstName(), is("Ronal"));
    }

    @Test
    public void testUpdateVet() {
        Vet created = vetService.create(new Vet("Ronal", "Gonzales"));
        created.setFirstName("Ronal Dante");
        Vet updated = vetService.update(created);
        assertThat(updated.getFirstName(), is("Ronal Dante"));
    }

    @Test
    public void testDeleteVet() throws VetNotFoundException {
        Vet created = vetService.create(new Vet("Temporal", "Vet"));
        vetService.delete(created.getId());
        try {
            vetService.findById(created.getId());
            assertThat(true, is(false));
        } catch (VetNotFoundException e) {
            assertThat(true, is(true));
        }
    }
}