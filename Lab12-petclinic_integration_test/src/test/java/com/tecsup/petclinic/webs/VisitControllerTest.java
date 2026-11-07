package com.tecsup.petclinic.webs;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class VisitControllerTest extends IntegrationTestSupport {
    @Test void crudVisit() throws Exception {
        MvcResult created=mockMvc.perform(post("/visits").contentType("application/json").content("{\"pet_id\":1,\"visit_date\":\"2026-11-07\",\"description\":\"Control\"}")).andExpect(status().isCreated()).andReturn(); long id=createdId(created);
        mockMvc.perform(get("/visits/"+id)).andExpect(status().isOk()).andExpect(jsonPath("$.description").value("Control"));
        mockMvc.perform(put("/visits/"+id).contentType("application/json").content("{\"description\":\"Seguimiento\"}")).andExpect(status().isOk());
        mockMvc.perform(get("/visits")).andExpect(status().isOk()).andExpect(jsonPath("$[?(@.description == 'Seguimiento')]").exists());
        mockMvc.perform(delete("/visits/"+id)).andExpect(status().isNoContent()); mockMvc.perform(get("/visits/"+id)).andExpect(status().isNotFound());
    }
}
