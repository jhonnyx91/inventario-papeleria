package com.papeleria.inventario.service;

import com.papeleria.inventario.dto.PrecioProductoResponse;
import com.papeleria.inventario.dto.ProductoRequest;
import com.papeleria.inventario.exception.ProductoNoEncontradoException;
import com.papeleria.inventario.model.Producto;
import com.papeleria.inventario.repository.ProductoRepository;
import org.springframework.stereotype.Service;

@Service
public class InventarioService {

    private final ProductoRepository productoRepository;

    // Inyección por constructor limpia y moderna (sin @Autowired)
    public InventarioService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public PrecioProductoResponse obtenerPrecioProducto(String codigo) {
        return productoRepository.findById(codigo)
                // Transformamos el Producto directamente en nuestro DTO Record usando su constructor compacto
                .map(producto -> new PrecioProductoResponse(producto.getPrecioVenta()))
                .orElseThrow(() -> new ProductoNoEncontradoException(codigo));
    }

    public void guardarProducto(ProductoRequest request) {
        // 1. Mapeamos los datos del Record (DTO) a una nueva entidad Producto tradicional de base de datos
        Producto nuevoProducto = new Producto(
                request.codigoBarras(),
                request.nombre(),
                request.precioVenta(),
                request.stock()
        );

        // 2. Lo guardamos en la base de datos local
        productoRepository.save(nuevoProducto);
    }

}

