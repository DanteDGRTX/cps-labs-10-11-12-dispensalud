package com.tecsup.petclinic.services;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
abstract class JdbcTestSupport {
    @Autowired protected JdbcTemplate jdbc;
    @AfterEach void cleanUp() { /* Spring rolls each test transaction back. */ }
    protected int rows(String table) { return jdbc.queryForObject("SELECT COUNT(*) FROM " + table, Integer.class); }
}
