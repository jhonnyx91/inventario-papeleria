package com.papeleria.inventario.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    private String codigoBarras; // El código que leerá el escáner USB
    private String nombre;       // Ej: "Cuaderno Norma 100H"
    private double precioVenta;  // Lo que paga el cliente
    private int stock;           // Cantidad disponible
}
