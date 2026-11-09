package com.tecsup.petclinic.services;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;

@SpringBootTest
public class OwnerServiceTest {
    @Autowired
    private OwnerService ownerService;

    @Test
    public void testFindOwnerById() throws OwnerNotFoundException {
        Owner owner = ownerService.findById(1L);
        assertThat(owner.getFirstName(), is("George"));
    }

    @Test
    public void testFindOwnerByLastName() {
        List<Owner> owners = ownerService.findByLastName("Davis");
        assertThat(owners.size(), is(2));
    }

    @Test
    public void testCreateOwner() {
        Owner owner = new Owner("Carlos", "Mamani", "Av. Ejercito 410", "Arequipa", "959441122");
        Owner created = ownerService.create(owner);
        assertThat(created.getId(), notNullValue());
        assertThat(created.getCity(), is("Arequipa"));
    }

    @Test
    public void testUpdateOwner() {
        Owner created = ownerService.create(new Owner("Carlos", "Mamani", "Av. Ejercito 410", "Arequipa", "959441122"));
        created.setCity("Cerro Colorado - Arequipa");
        Owner updated = ownerService.update(created);
        assertThat(updated.getCity(), is("Cerro Colorado - Arequipa"));
    }

    @Test
    public void testDeleteOwner() throws OwnerNotFoundException {
        Owner created = ownerService.create(new Owner("Rosa", "Mamani", "Calle Melgar 205", "Arequipa", "958112233"));
        ownerService.delete(created.getId());
        try {
            ownerService.findById(created.getId());
            assertThat(true, is(false));
        } catch (OwnerNotFoundException e) {
            assertThat(true, is(true));
        }
    }
}