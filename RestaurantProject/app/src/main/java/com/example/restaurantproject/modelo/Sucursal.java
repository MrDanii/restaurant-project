package com.example.restaurantproject.modelo;

public class Sucursal {
    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmpresa_rfc() {
        return Empresa_rfc;
    }

    public void setEmpresa_rfc(String empresa_rfc) {
        Empresa_rfc = empresa_rfc;
    }

    int idSucursal, telefono;
    String nombre, direccion,Empresa_rfc;

}
