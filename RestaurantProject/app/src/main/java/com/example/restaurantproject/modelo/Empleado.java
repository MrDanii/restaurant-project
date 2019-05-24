package com.example.restaurantproject.modelo;

public class Empleado {

    String clave_empleado, contrasena,nombre,apellido_paterni,apellido_materno,puesto,fecha_registro;
    int activo;

    public String getClave_empleado() {
        return clave_empleado;
    }

    public void setClave_empleado(String clave_empleado) {
        this.clave_empleado = clave_empleado;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_paterni() {
        return apellido_paterni;
    }

    public void setApellido_paterni(String apellido_paterni) {
        this.apellido_paterni = apellido_paterni;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public int getSucursal_idSucursal() {
        return Sucursal_idSucursal;
    }

    public void setSucursal_idSucursal(int sucursal_idSucursal) {
        Sucursal_idSucursal = sucursal_idSucursal;
    }

    int Sucursal_idSucursal;
}
