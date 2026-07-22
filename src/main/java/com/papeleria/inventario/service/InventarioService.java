package com.papeleria.inventario.service;

import com.papeleria.inventario.dto.ProductoRequest;
import com.papeleria.inventario.dto.ProductoUpdate;
import com.papeleria.inventario.dto.ProductoResponse;
import com.papeleria.inventario.exception.ProductoNoEncontradoException;
import com.papeleria.inventario.model.Producto;
import com.papeleria.inventario.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventarioService {

    private final ProductoRepository productoRepository;

    // Inyección por constructor limpia y moderna (sin @Autowired)
    public InventarioService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public ProductoResponse obtenerProductoPorCodigo(String codigo) {
        return productoRepository.findById(codigo)
                // 🚀 Transformamos la entidad en el DTO completo de 4 campos
                .map(producto -> new ProductoResponse(
                        producto.getCodigoBarras(),
                        producto.getNombre(),
                        producto.getPrecioVenta(),
                        producto.getStock()
                ))
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

    public ProductoUpdate actualizarProductoParcial(String codigo, ProductoUpdate dto) {
        Producto productoExistente = productoRepository.findById(codigo)
                .orElseThrow(() -> new ProductoNoEncontradoException(codigo));

        // 💡 Si 'dto.nombre()' no es nulo, actualiza. Si es nulo, deja el nombre existente.
        Optional.ofNullable(dto.nombre()).ifPresent(productoExistente::setNombre);
        Optional.ofNullable(dto.precioVenta()).ifPresent(productoExistente::setPrecioVenta);
        Optional.ofNullable(dto.stock()).ifPresent(productoExistente::setStock);

        productoRepository.save(productoExistente);

        // Devolvemos el estado final real del producto mezclando lo viejo y lo nuevo
        return new ProductoUpdate(
                productoExistente.getNombre(),
                productoExistente.getPrecioVenta(),
                productoExistente.getStock()
        );
    }

    public void eliminarProducto(String codigo) {
        // 1. Validamos la existencia. Si no está, frena de inmediato y lanza el error 404
        Producto producto = productoRepository.findById(codigo)
                .orElseThrow(() -> new ProductoNoEncontradoException(codigo));

        // 2. Si existía, lo borramos físicamente de la base de datos local
        productoRepository.delete(producto);
    }

    public List<ProductoResponse> listarTodoElInventario() {
        return productoRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Producto::getNombre))
                .map(p -> new ProductoResponse(
                        p.getCodigoBarras(),
                        p.getNombre(),
                        p.getPrecioVenta(),
                        p.getStock()
                ))
                .collect(Collectors.toList());
    }
}

