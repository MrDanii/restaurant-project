package com.example.restaurantproject.modelo;

public class Mesa {
    int idMesa,numero_sillas;

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public int getNumero_sillas() {
        return numero_sillas;
    }

    public void setNumero_sillas(int numero_sillas) {
        this.numero_sillas = numero_sillas;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getEmpleado_clave_empleado() {
        return Empleado_clave_empleado;
    }

    public void setEmpleado_clave_empleado(String empleado_clave_empleado) {
        Empleado_clave_empleado = empleado_clave_empleado;
    }

    boolean disponible;
    String Empleado_clave_empleado;
}
