package com.papeleria.inventario.dto;

public record ProductoUpdate(
        String nombre,
        Double precioVenta,  // 🚀 Asegúrate de usar 'Double' con D mayúscula
        Integer stock        // 🚀 Cambia 'int' por 'Integer' con I mayúscula
) {}

