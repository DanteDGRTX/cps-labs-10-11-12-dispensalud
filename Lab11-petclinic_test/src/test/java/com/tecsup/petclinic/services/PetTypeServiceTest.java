package com.tecsup.petclinic.services;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.tecsup.petclinic.entities.PetType;
import com.tecsup.petclinic.exception.PetTypeNotFoundException;

@SpringBootTest
public class PetTypeServiceTest {
    @Autowired
    private PetTypeService petTypeService;

    @Test
    public void testFindTypeById() throws PetTypeNotFoundException {
        PetType type = petTypeService.findById(1L);
        assertThat(type.getName(), is("cat"));
    }

    @Test
    public void testCreateType() {
        PetType type = new PetType("comprimido_recubierto_50mg");
        PetType created = petTypeService.create(type);
        assertThat(created.getId(), notNullValue());
        assertThat(created.getName(), is("comprimido_recubierto_50mg"));
    }

    @Test
    public void testUpdateType() {
        PetType created = petTypeService.create(new PetType("tableta_oral"));
        created.setName("tableta_oral_losartan_50mg");
        PetType updated = petTypeService.update(created);
        assertThat(updated.getName(), is("tableta_oral_losartan_50mg"));
    }

    @Test
    public void testDeleteType() throws PetTypeNotFoundException {
        PetType created = petTypeService.create(new PetType("tipo_temporal"));
        petTypeService.delete(created.getId());
        try {
            petTypeService.findById(created.getId());
            assertThat(true, is(false));
        } catch (PetTypeNotFoundException e) {
            assertThat(true, is(true));
        }
    }
}