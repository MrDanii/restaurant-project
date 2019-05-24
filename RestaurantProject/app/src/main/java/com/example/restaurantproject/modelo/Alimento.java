package com.example.restaurantproject.modelo;

public class Alimento {
    int idAlimento, existencia,Categoria_idCategoria;
    String nombre;

    public int getIdAlimento() {
        return idAlimento;
    }

    public void setIdAlimento(int idAlimento) {
        this.idAlimento = idAlimento;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public int getCategoria_idCategoria() {
        return Categoria_idCategoria;
    }

    public void setCategoria_idCategoria(int categoria_idCategoria) {
        Categoria_idCategoria = categoria_idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    float precio;
}
