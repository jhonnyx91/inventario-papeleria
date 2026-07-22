package com.papeleria.inventario.service;

import com.papeleria.inventario.dto.DetalleVentaRequest;
import com.papeleria.inventario.dto.VentaRequest;
import com.papeleria.inventario.exception.ProductoNoEncontradoException;
import com.papeleria.inventario.model.Producto;
import com.papeleria.inventario.model.Venta;
import com.papeleria.inventario.repository.ProductoRepository;
import com.papeleria.inventario.repository.VentaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;

    // Inyección por constructor de ambos repositorios
    public VentaService(VentaRepository ventaRepository, ProductoRepository productoRepository) {
        this.ventaRepository = ventaRepository;
        this.productoRepository = productoRepository;
    }

    @Transactional
    public void registrarVenta(VentaRequest request) {
        double totalFactura = 0.0;

        for (DetalleVentaRequest detalle : request.productos()) {
            Producto producto = productoRepository.findById(detalle.codigoBarras())
                    .orElseThrow(() -> new ProductoNoEncontradoException(detalle.codigoBarras()));

            if (producto.getStock() < detalle.cantidad()) {
                throw new RuntimeException("Stock insuficiente para: " + producto.getNombre() +
                        ". Solo quedan " + producto.getStock() + " unidades.");
            }

            producto.setStock(producto.getStock() - detalle.cantidad());
            productoRepository.save(producto);

            double subtotal = producto.getPrecioVenta() * detalle.cantidad();
            totalFactura += subtotal;
        }

        Venta facturaMaestra = new Venta(
                request.productos().size(),
                totalFactura,
                LocalDateTime.now()
        );

        ventaRepository.save(facturaMaestra);
    }

}
