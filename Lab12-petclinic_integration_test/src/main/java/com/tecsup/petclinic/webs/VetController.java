package com.tecsup.petclinic.webs;
import java.util.Set;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController @RequestMapping("/vets")
public class VetController extends AbstractCrudController { public VetController(JdbcTemplate jdbc){super(jdbc,"vets",Set.of("first_name","last_name","email"));} }
