package com.tecsup.petclinic.webs;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OwnerController.class)
class OwnerControllerMockitoTest {
    @Autowired MockMvc mockMvc;
    @MockBean JdbcTemplate jdbc;
    @Test void listUsesMockedRepository() throws Exception {
        when(jdbc.queryForList("SELECT * FROM owners")).thenReturn(List.of(Map.<String,Object>of("first_name","Rosa","consentimiento_informado",true)));
        mockMvc.perform(get("/owners")).andExpect(status().isOk()).andExpect(jsonPath("$[0].consentimiento_informado").value(true));
    }
}
