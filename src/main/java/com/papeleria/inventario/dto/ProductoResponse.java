package com.papeleria.inventario.dto;

public record ProductoResponse(
        String codigoBarras,
        String nombre,
        double precioVenta,
        int stock
) {}
