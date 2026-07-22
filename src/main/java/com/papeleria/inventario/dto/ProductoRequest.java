package com.papeleria.inventario.dto;

public record ProductoRequest(
        String codigoBarras,
        String nombre,
        double precioVenta,
        int stock
) {}
