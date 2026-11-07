package com.tecsup.petclinic.webs;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class VetControllerTest extends IntegrationTestSupport {
    @Test void crudAndNotFound() throws Exception {
        mockMvc.perform(get("/vets")).andExpect(status().isOk()).andExpect(jsonPath("$[0].first_name").value("Rita"));
        mockMvc.perform(get("/vets/1")).andExpect(status().isOk()).andExpect(jsonPath("$.last_name").value("Salas"));
        MvcResult created=mockMvc.perform(post("/vets").contentType("application/json").content("{\"first_name\":\"Nora\",\"last_name\":\"Vega\",\"email\":\"nora@test\"}")).andExpect(status().isCreated()).andReturn();
        long id=createdId(created);
        mockMvc.perform(put("/vets/"+id).contentType("application/json").content("{\"last_name\":\"Rios\"}")).andExpect(status().isOk());
        mockMvc.perform(get("/vets/"+id)).andExpect(status().isOk()).andExpect(jsonPath("$.last_name").value("Rios"));
        mockMvc.perform(delete("/vets/"+id)).andExpect(status().isNoContent()); mockMvc.perform(get("/vets/"+id)).andExpect(status().isNotFound());
    }
}
