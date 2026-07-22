package com.papeleria.inventario.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Factura #1, #2, #3 automático
    private Long id;

    private int cantidadItems;   // Cuántos productos diferentes se llevaron en total
    private double totalVenta;    // El dinero total acumulado de ese ticket
    private LocalDateTime fechaHora;

    public Venta(int cantidadItems, double totalVenta, LocalDateTime fechaHora) {
        this.cantidadItems = cantidadItems;
        this.totalVenta = totalVenta;
        this.fechaHora = fechaHora;
    }
}
