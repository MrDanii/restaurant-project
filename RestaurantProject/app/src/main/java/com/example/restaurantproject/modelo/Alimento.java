package com.example.restaurantproject.modelo;

public class Alimento {
    private int idAlimento, existencia,Categoria_idCategoria;
    private long idImagen;
    private String nombre;

    public Alimento(int categoria_idCategoria, String nombre, float precio) {
        Categoria_idCategoria = categoria_idCategoria;
        this.nombre = nombre;
        this.precio = precio;
    }

    public long getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(long idImagen) {
        this.idImagen = idImagen;
    }

    public Alimento() {
    }

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

    private float precio;
}
