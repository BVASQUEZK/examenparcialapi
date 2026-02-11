package com.bardales.examenparcialapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bardales.examenparcialapi.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
