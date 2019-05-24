package com.example.restaurantproject.modelo;

public class Orden {
    int id_orden,numero_mesa;

    public int getId_orden() {
        return id_orden;
    }

    public void setId_orden(int id_orden) {
        this.id_orden = id_orden;
    }

    public int getNumero_mesa() {
        return numero_mesa;
    }

    public void setNumero_mesa(int numero_mesa) {
        this.numero_mesa = numero_mesa;
    }

    public String getFecha_orden() {
        return fecha_orden;
    }

    public void setFecha_orden(String fecha_orden) {
        this.fecha_orden = fecha_orden;
    }

    public String getEmpleado_clave_empleado() {
        return Empleado_clave_empleado;
    }

    public void setEmpleado_clave_empleado(String empleado_clave_empleado) {
        Empleado_clave_empleado = empleado_clave_empleado;
    }

    public boolean isEntregada() {
        return entregada;
    }

    public void setEntregada(boolean entregada) {
        this.entregada = entregada;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getPropina() {
        return propina;
    }

    public void setPropina(double propina) {
        this.propina = propina;
    }

    String fecha_orden,Empleado_clave_empleado;
    boolean entregada;
    double total,propina;
}
