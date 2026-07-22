package com.papeleria.inventario.dto;

import java.util.List;

public record VentaRequest(
        List<DetalleVentaRequest> productos // 🚀 Soporta todos los artículos a la vez
) {}
