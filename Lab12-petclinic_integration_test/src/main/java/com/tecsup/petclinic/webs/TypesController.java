package com.tecsup.petclinic.webs;
import java.util.Set;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController @RequestMapping("/types")
public class TypesController extends AbstractCrudController { public TypesController(JdbcTemplate jdbc){super(jdbc,"types",Set.of("name"));} }
