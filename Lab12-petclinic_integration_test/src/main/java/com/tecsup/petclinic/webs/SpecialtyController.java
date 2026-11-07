package com.tecsup.petclinic.webs;
import java.util.Set;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController @RequestMapping("/specialties")
public class SpecialtyController extends AbstractCrudController { public SpecialtyController(JdbcTemplate jdbc){super(jdbc,"specialties",Set.of("name"));} }
