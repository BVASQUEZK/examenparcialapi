package com.bardales.examenparcialapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tallas")
public class Talla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "relacion", nullable = false, length = 150)
    private String relacion;

    @Column(name = "prenda", nullable = false, length = 100)
    private String prenda;

    @Column(name = "talla", nullable = false, length = 20)
    private String talla;

    @Column(name = "notas", columnDefinition = "TEXT")
    private String notas;

    @Column(name = "activo", nullable = false)
    private Boolean activo;

    public Talla() {
    }

    public Talla(Integer id, String nombre, String relacion, String prenda, String talla, String notas, Boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.relacion = relacion;
        this.prenda = prenda;
        this.talla = talla;
        this.notas = notas;
        this.activo = activo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRelacion() {
        return relacion;
    }

    public void setRelacion(String relacion) {
        this.relacion = relacion;
    }

    public String getPrenda() {
        return prenda;
    }

    public void setPrenda(String prenda) {
        this.prenda = prenda;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
