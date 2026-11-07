package com.tecsup.petclinic.webs;
import java.util.Set;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController @RequestMapping("/owners")
public class OwnerController extends AbstractCrudController { public OwnerController(JdbcTemplate jdbc){super(jdbc,"owners",Set.of("first_name","last_name","city","consentimiento_informado"));} }
