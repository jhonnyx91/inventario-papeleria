package com.papeleria.inventario.controller;

import com.papeleria.inventario.dto.DetalleVentaRequest;
import com.papeleria.inventario.dto.VentaRequest;
import com.papeleria.inventario.service.VentaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ventas") // 🚀 Ruta empresarial propia para el módulo de facturación
public class VentaController {

    private final VentaService ventaService;

    // Inyección por constructor limpia y moderna
    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    // Ruta final: POST http://localhost:8080/api/ventas
    @PostMapping
    public void realizarVenta(@RequestBody VentaRequest request) {
        ventaService.registrarVenta(request); // 🚀 Llama al servicio de ventas
    }
}
