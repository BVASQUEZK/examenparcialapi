package com.bardales.examenparcialapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bardales.examenparcialapi.entity.Producto;
import com.bardales.examenparcialapi.repository.ProductoRepository;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    private final ProductoRepository productoRepository;

    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @GetMapping("/listar")
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    @PostMapping("/insertar")
    public Producto insertarProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }
}
