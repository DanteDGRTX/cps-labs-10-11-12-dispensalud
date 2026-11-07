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

@WebMvcTest(VetController.class)
class VetControllerMockitoTest {
    @Autowired MockMvc mockMvc;
    @MockBean JdbcTemplate jdbc;
    @Test void listUsesMockedRepository() throws Exception {
        when(jdbc.queryForList("SELECT * FROM vets")).thenReturn(List.of(Map.<String,Object>of("first_name","Rita","last_name","Salas")));
        mockMvc.perform(get("/vets")).andExpect(status().isOk()).andExpect(jsonPath("$[0].first_name").value("Rita"));
    }
}
