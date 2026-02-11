package com.bardales.examenparcialapi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bardales.examenparcialapi.entity.Talla;

@RestController
@RequestMapping("/tallas")
@CrossOrigin(origins = "*")
public class TallaController {

    private final JdbcTemplate jdbcTemplate;

    public TallaController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public List<Talla> listar() {
        return jdbcTemplate.query("CALL sp_listar_tallas()", (rs, rowNum) -> mapRow(rs));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Talla> detalle(@PathVariable Integer id) {
        List<Talla> lista = jdbcTemplate.query(
            "CALL sp_detalle_talla(?)",
            (rs, rowNum) -> mapRow(rs),
            id
        );
        return lista.stream().findFirst().map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Talla> crear(@RequestBody Talla talla) {
        Boolean activo = talla.getActivo() != null ? talla.getActivo() : Boolean.TRUE;
        Map<String, Object> out = jdbcTemplate.queryForMap(
            "CALL sp_insertar_talla(?,?,?,?,?,?)",
            talla.getNombre(),
            talla.getRelacion(),
            talla.getPrenda(),
            talla.getTalla(),
            talla.getNotas(),
            activo
        );
        Number idGen = (Number) out.get("id_generado");
        if (idGen == null) {
            return ResponseEntity.internalServerError().build();
        }
        return detalle(idGen.intValue());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Talla> editar(@PathVariable Integer id, @RequestBody Talla input) {
        if (detalle(id).getBody() == null) {
            return ResponseEntity.notFound().build();
        }
        Boolean activo = input.getActivo() != null ? input.getActivo() : Boolean.TRUE;
        jdbcTemplate.queryForMap(
            "CALL sp_editar_talla(?,?,?,?,?,?,?)",
            id,
            input.getNombre(),
            input.getRelacion(),
            input.getPrenda(),
            input.getTalla(),
            input.getNotas(),
            activo
        );
        return detalle(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (detalle(id).getBody() == null) {
            return ResponseEntity.notFound().build();
        }
        jdbcTemplate.queryForMap("CALL sp_eliminar_talla(?)", id);
        return ResponseEntity.noContent().build();
    }

    private Talla mapRow(java.sql.ResultSet rs) throws java.sql.SQLException {
        return new Talla(
            rs.getInt("id"),
            rs.getString("nombre"),
            rs.getString("relacion"),
            rs.getString("prenda"),
            rs.getString("talla"),
            rs.getString("notas"),
            rs.getBoolean("activo")
        );
    }
}
