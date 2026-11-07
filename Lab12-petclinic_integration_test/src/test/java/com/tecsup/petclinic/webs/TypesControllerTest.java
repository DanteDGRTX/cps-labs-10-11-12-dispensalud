package com.tecsup.petclinic.webs;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TypesControllerTest extends IntegrationTestSupport {
    @Test void crudType() throws Exception {
        MvcResult created=mockMvc.perform(post("/types").contentType("application/json").content("{\"name\":\"Gato\"}")).andExpect(status().isCreated()).andReturn(); long id=createdId(created);
        mockMvc.perform(get("/types/"+id)).andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Gato"));
        mockMvc.perform(put("/types/"+id).contentType("application/json").content("{\"name\":\"Ave\"}")).andExpect(status().isOk());
        mockMvc.perform(get("/types")).andExpect(status().isOk()).andExpect(jsonPath("$[?(@.name == 'Ave')]").exists());
        mockMvc.perform(delete("/types/"+id)).andExpect(status().isNoContent()); mockMvc.perform(get("/types/"+id)).andExpect(status().isNotFound());
    }
}
