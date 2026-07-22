package com.papeleria.inventario.exception;

public class ProductoNoEncontradoException extends RuntimeException {
    public ProductoNoEncontradoException(String codigo) {
        super("El producto con código " + codigo + " no fue encontrado en el inventario.");
    }
}