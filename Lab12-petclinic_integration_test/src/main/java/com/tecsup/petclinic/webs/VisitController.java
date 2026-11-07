package com.tecsup.petclinic.webs;
import java.util.Set;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController @RequestMapping("/visits")
public class VisitController extends AbstractCrudController { public VisitController(JdbcTemplate jdbc){super(jdbc,"visits",Set.of("pet_id","visit_date","description"));} }
