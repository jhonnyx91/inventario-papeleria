package com.papeleria.inventario.dto;

public record DetalleVentaRequest(
        String codigoBarras,
        int cantidad
) {}
