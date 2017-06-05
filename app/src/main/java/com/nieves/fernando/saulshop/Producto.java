package com.nieves.fernando.saulshop;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by usuario on 26/05/2017.
 */

public class Producto implements Serializable{

    private String codigo_producto;
    private String nombre_producto;
    private String descripcion_producto;
    private String precio;
    private String cantidad_stock;


    List<Producto> catalogoe;

    // This method creates an ArrayList that has three Person objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.

    //Constructor de Producto
    public Producto(String codigo_producto, String nombre_producto, String descripcion_producto, String precio, String cantidad_stock) {
        this.codigo_producto = codigo_producto;
        this.nombre_producto = nombre_producto;
        this.descripcion_producto = descripcion_producto;
        this.precio = precio;
        this.cantidad_stock = cantidad_stock;
    }

    //Getters de Producto

    public String getCodigo_producto() {
        return codigo_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public String getDescripcion_producto() {
        return descripcion_producto;
    }

    public String getPrecio() {
        return precio;
    }

    public String getCantidad_stock() {
        return cantidad_stock;
    }

    //Setters de Producto

    public void setCodigo_producto(String codigo_producto) {
        this.codigo_producto = codigo_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public void setDescripcion_producto(String descripcion_producto) {
        this.descripcion_producto = descripcion_producto;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public void setCantidad_stock(String cantidad_stock) {
        this.cantidad_stock = cantidad_stock;
    }
}
