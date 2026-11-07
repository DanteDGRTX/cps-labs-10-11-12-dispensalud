package com.tecsup.petclinic.webs;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class SpecialtyControllerTest extends IntegrationTestSupport {
    @Test void crudAndMissingSpecialty() throws Exception {
        MvcResult created=mockMvc.perform(post("/specialties").contentType("application/json").content("{\"name\":\"Oftalmologia\"}")).andExpect(status().isCreated()).andReturn(); long id=createdId(created);
        mockMvc.perform(get("/specialties")).andExpect(status().isOk()).andExpect(jsonPath("$[0].name").value("Cardiología"));
        mockMvc.perform(put("/specialties/"+id).contentType("application/json").content("{\"name\":\"Cirugia\"}")).andExpect(status().isOk());
        mockMvc.perform(get("/specialties/"+id)).andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Cirugia"));
        mockMvc.perform(delete("/specialties/"+id)).andExpect(status().isNoContent()); mockMvc.perform(get("/specialties/"+id)).andExpect(status().isNotFound());
    }
}
