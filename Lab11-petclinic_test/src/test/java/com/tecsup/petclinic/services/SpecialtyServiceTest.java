package com.tecsup.petclinic.services;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exception.SpecialtyNotFoundException;

@SpringBootTest
public class SpecialtyServiceTest {
    @Autowired
    private SpecialtyService specialtyService;

    @Test
    public void testFindSpecialtyById() throws SpecialtyNotFoundException {
        Specialty sp = specialtyService.findById(1L);
        assertThat(sp.getName(), is("radiology"));
    }

    @Test
    public void testCreateSpecialty() {
        Specialty sp = new Specialty("geriatria_farmacologica");
        Specialty created = specialtyService.create(sp);
        assertThat(created.getId(), notNullValue());
        assertThat(created.getName(), is("geriatria_farmacologica"));
    }

    @Test
    public void testUpdateSpecialty() {
        Specialty created = specialtyService.create(new Specialty("compartimento_1_led"));
        created.setName("compartimento_1_led_azul_activo");
        Specialty updated = specialtyService.update(created);
        assertThat(updated.getName(), is("compartimento_1_led_azul_activo"));
    }

    @Test
    public void testDeleteSpecialty() throws SpecialtyNotFoundException {
        Specialty created = specialtyService.create(new Specialty("temp_specialty"));
        specialtyService.delete(created.getId());
        try {
            specialtyService.findById(created.getId());
            assertThat(true, is(false));
        } catch (SpecialtyNotFoundException e) {
            assertThat(true, is(true));
        }
    }
}