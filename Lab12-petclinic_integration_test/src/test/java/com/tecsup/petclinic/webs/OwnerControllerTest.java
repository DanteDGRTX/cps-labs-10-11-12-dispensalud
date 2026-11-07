package com.tecsup.petclinic.webs;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class OwnerControllerTest extends IntegrationTestSupport {
    @Test void crudAndConsentField() throws Exception {
        MvcResult created=mockMvc.perform(post("/owners").contentType("application/json").content("{\"first_name\":\"Rosa\",\"last_name\":\"Mamani\",\"city\":\"Lima\",\"consentimiento_informado\":true}")).andExpect(status().isCreated()).andReturn();
        long id=createdId(created);
        mockMvc.perform(get("/owners/"+id)).andExpect(status().isOk()).andExpect(jsonPath("$.consentimiento_informado").value(true));
        mockMvc.perform(put("/owners/"+id).contentType("application/json").content("{\"city\":\"Cusco\"}")).andExpect(status().isOk());
        mockMvc.perform(get("/owners")).andExpect(status().isOk()).andExpect(jsonPath("$[?(@.first_name == 'Rosa')]").exists());
        mockMvc.perform(delete("/owners/"+id)).andExpect(status().isNoContent()); mockMvc.perform(get("/owners/"+id)).andExpect(status().isNotFound());
    }
}
