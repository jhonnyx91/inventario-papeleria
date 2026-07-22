package com.papeleria.inventario.controller;

import com.papeleria.inventario.dto.PrecioProductoResponse;
import com.papeleria.inventario.dto.ProductoRequest;
import com.papeleria.inventario.dto.ProductoResponse;
import com.papeleria.inventario.dto.ProductoUpdate;
import com.papeleria.inventario.service.InventarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final InventarioService inventarioService;

    // Inyección por constructor (Aquí sí ves 'inventarioService' con minúscula inicial por ser variable)
    public ProductoController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    // Ruta para consultar: GET http://localhost:8080/api/productos/{codigo}/precio
    @GetMapping("/{codigo}/precio")
    public PrecioProductoResponse obtenerPrecio(@PathVariable String codigo) {
        return inventarioService.obtenerPrecioProducto(codigo);
    }

    @PostMapping // Ruta: POST http://localhost:8080/api/productos
    @ResponseStatus(HttpStatus.CREATED) // Devuelve un código HTTP 201 (Creado) automático si todo sale bien
    public void crearProducto(@RequestBody ProductoRequest request) {
        inventarioService.guardarProducto(request);
    }

    @PatchMapping("/{codigo}") // PATCH http://localhost:8080/api/productos/{codigo}
    public ProductoUpdate actualizarParcial(@PathVariable String codigo, @RequestBody ProductoUpdate dto) {
        return inventarioService.actualizarProductoParcial(codigo, dto);
    }

    @DeleteMapping("/{codigo}") // Ruta: DELETE http://localhost:8080/api/productos/{codigo}
    @ResponseStatus(HttpStatus.NO_CONTENT) // Devuelve un código HTTP 204 (No Content) estándar para borrados exitosos
    public void eliminar(@PathVariable String codigo) {
        inventarioService.eliminarProducto(codigo);
    }

    @GetMapping // Ruta: GET http://localhost:8080/api/productos
    public List<ProductoResponse> listarTodos() {
        return inventarioService.listarTodoElInventario();
    }

}

