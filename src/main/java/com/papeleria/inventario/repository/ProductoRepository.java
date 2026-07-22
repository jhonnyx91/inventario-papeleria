package com.papeleria.inventario.repository;

import com.papeleria.inventario.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, String> {
    // Al heredar de JpaRepository, Spring genera automáticamente en memoria
    // todos los métodos para buscar, guardar, borrar y actualizar en la base de datos.
}
