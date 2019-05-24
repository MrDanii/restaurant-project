package com.example.restaurantproject.modelo;

public class DetalleOrden {
    public int getIdDetalleOrden() {
        return idDetalleOrden;
    }

    public void setIdDetalleOrden(int idDetalleOrden) {
        this.idDetalleOrden = idDetalleOrden;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getAlimento_idAlimento() {
        return Alimento_idAlimento;
    }

    public void setAlimento_idAlimento(int alimento_idAlimento) {
        Alimento_idAlimento = alimento_idAlimento;
    }

    public int getOrden_id_orden() {
        return Orden_id_orden;
    }

    public void setOrden_id_orden(int orden_id_orden) {
        Orden_id_orden = orden_id_orden;
    }

    int idDetalleOrden,cantidad,Alimento_idAlimento,Orden_id_orden;

}
