package com.tecsup.petclinic.services;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.entities.Vet;

@SpringBootTest
public class VetSpecialtyServiceTest {
    @Autowired
    private VetSpecialtyService vetSpecialtyService;

    @Test
    public void testAssignSpecialtyToVet() {
        vetSpecialtyService.assignSpecialty(1L, 2L); // Asigna surgery (2) a James Carter (1)
        List<Specialty> specs = vetSpecialtyService.findSpecialtiesByVetId(1L);
        assertThat(specs.isEmpty(), is(false));
    }

    @Test
    public void testFindSpecialtiesByVetId() {
        List<Specialty> specs = vetSpecialtyService.findSpecialtiesByVetId(3L); // Linda Douglas -> surgery & dentistry
        assertThat(specs.size(), is(2));
    }

    @Test
    public void testFindVetsBySpecialtyId() {
        List<Vet> vets = vetSpecialtyService.findVetsBySpecialtyId(2L); // surgery
        assertThat(vets.size() >= 2, is(true));
    }

    @Test
    public void testDeleteVetSpecialtyRelation() {
        vetSpecialtyService.assignSpecialty(6L, 1L);
        vetSpecialtyService.removeSpecialtyFromVet(6L, 1L);
        List<Specialty> specs = vetSpecialtyService.findSpecialtiesByVetId(6L);
        assertThat(specs.size(), is(0));
    }
}