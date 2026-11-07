package com.tecsup.petclinic.webs;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

abstract class AbstractCrudController {
    private final JdbcTemplate jdbc;
    private final String table;
    private final Set<String> columns;
    AbstractCrudController(JdbcTemplate jdbc, String table, Set<String> columns) { this.jdbc=jdbc; this.table=table; this.columns=columns; }

    @GetMapping public List<Map<String,Object>> list() { return jdbc.queryForList("SELECT * FROM " + table).stream().map(this::lowerCaseKeys).toList(); }
    @GetMapping("/{id}") public ResponseEntity<?> get(@PathVariable long id) {
        List<Map<String,Object>> rows=jdbc.queryForList("SELECT * FROM " + table + " WHERE id=?", id);
        return rows.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(lowerCaseKeys(rows.get(0)));
    }
    @PostMapping public ResponseEntity<?> create(@RequestBody Map<String,Object> body) {
        List<String> fields=body.keySet().stream().filter(columns::contains).sorted().toList();
        if(fields.isEmpty()) return ResponseEntity.badRequest().body(Map.of("error","No hay campos válidos"));
        String placeholders=String.join(",", java.util.Collections.nCopies(fields.size(),"?"));
        String sql="INSERT INTO " + table + " (" + String.join(",",fields) + ") VALUES (" + placeholders + ")";
        KeyHolder key=new GeneratedKeyHolder();
        jdbc.update(connection->{PreparedStatement ps=connection.prepareStatement(sql,new String[]{"id"}); for(int i=0;i<fields.size();i++) ps.setObject(i+1,body.get(fields.get(i))); return ps;},key);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("id",key.getKey().longValue()));
    }
    @PutMapping("/{id}") public ResponseEntity<?> update(@PathVariable long id,@RequestBody Map<String,Object> body) {
        List<String> fields=body.keySet().stream().filter(columns::contains).sorted().toList();
        if(fields.isEmpty()) return ResponseEntity.badRequest().body(Map.of("error","No hay campos válidos"));
        String set=String.join(",",fields.stream().map(c->c+"=?").toList()); Object[] values=new Object[fields.size()+1];
        for(int i=0;i<fields.size();i++) values[i]=body.get(fields.get(i)); values[values.length-1]=id;
        return jdbc.update("UPDATE " + table + " SET " + set + " WHERE id=?",values)==0 ? ResponseEntity.notFound().build() : ResponseEntity.ok(Map.of("id",id));
    }
    @DeleteMapping("/{id}") public ResponseEntity<?> delete(@PathVariable long id) {
        return jdbc.update("DELETE FROM " + table + " WHERE id=?",id)==0 ? ResponseEntity.notFound().build() : ResponseEntity.noContent().build();
    }
    private Map<String,Object> lowerCaseKeys(Map<String,Object> row) { Map<String,Object> normalized=new java.util.LinkedHashMap<>(); row.forEach((k,v)->normalized.put(k.toLowerCase(java.util.Locale.ROOT),v)); return normalized; }
}
