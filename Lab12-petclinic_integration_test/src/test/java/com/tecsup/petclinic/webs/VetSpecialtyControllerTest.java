package com.tecsup.petclinic.webs;

import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class  VetSpecialtyControllerTest extends IntegrationTestSupport {
    @Test void assignFindAndDeleteRelation() throws Exception {
        mockMvc.perform(post("/vet-specialties").contentType("application/json").content("{\"vetId\":1,\"specialtyId\":1}")).andExpect(status().isCreated());
        mockMvc.perform(post("/vet-specialties").contentType("application/json").content("{\"vetId\":1,\"specialtyId\":1}")).andExpect(status().isConflict());
        mockMvc.perform(get("/vet-specialties/vet/1")).andExpect(status().isOk()).andExpect(jsonPath("$[0].specialty_id").value(1));
        mockMvc.perform(get("/vet-specialties/specialty/1")).andExpect(status().isOk()).andExpect(jsonPath("$[0].vet_id").value(1));
        mockMvc.perform(delete("/vet-specialties/1/1")).andExpect(status().isNoContent());
        mockMvc.perform(delete("/vet-specialties/1/1")).andExpect(status().isNotFound());
    }
}
