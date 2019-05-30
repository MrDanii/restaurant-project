package com.example.restaurantproject.persistencia;

import android.provider.BaseColumns;

public final class RestaurantContract {

    private RestaurantContract() {
    }

    public static class TablaCategoria{
        public static final String TABLE_NAME = "Categoria";
        public static final String COLUMN_ID_CATEGORIA = "idCategoria";
        public static final String COLUMN_NOMBRE = "nombre";
        public static final String COLUMN_DESCRIPCION = "descripcion";
    }

    public static class TablaAlimento {
        public static final String TABLE_NAME = "Alimento";
        public static final String COLUMN_ID_ALIMENTO = "idAlimento";
        public static final String COLUMN_NOMBRE = "nombre";
        public static final String COLUMN_PRECIO = "precio";
        public static final String COLUMN_EXISTENCIA = "existencia";
        public static final String COLUMN_ID_CATEGORIA = "Categoria_idCategoria";
    }

    public static class TablaEmpleado {
        public static final String TABLE_NAME = "Empleado";
        public static final String COLUMN_CLAVE_EMPLEADO= "clave_empleado";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_NOMBRE = "nombre";
        public static final String COLUMN_APELLIDOP = "apellido_paterno";
        public static final String COLUMN_APELLIDOM = "apellido_materno";
        public static final String COLUMN_PUESTO = "puesto";
        public static final String COLUMN_ACTIVO = "activo";
        public static final String COLUMN_FECHA_REGISTRO = "fecha_registro";
        public static final String COLUMN_SUCURSAL_ID = "Sucursal_idSucursal";
    }

    public static class TablaOrden {
        public static final String TABLE_NAME = "Orden";
        public static final String COLUMN_ID_ORDEN = "id_orden";
        public static final String COLUMN_FECHA_ORDEN = "fecha_orden";
        public static final String COLUMN_ENTREGADA = "entregada";
        public static final String COLUMN_NUMERO_MESA = "numero_mesa";
        public static final String COLUMN_FINALIZADA = "finalizada";
        public static final String COLUMN_TOTAL = "total";
        public static final String COLUMN_PROPINA = "propina";
        public static final String COLUMN_IDEMPLEADO = "Empleado_clave_empleado";
    }

    public static class TablaDetalleOrden {
        public static final String TABLE_NAME = "DetalleOrden";
        public static final String COLUMN_ID_DETALLE_ORDEN = "id_detalle_orden";
        public static final String COLUMN_CANTIDAD = "cantidad";
        public static final String COLUMN_ID_ALIMENTO = "Alimento_idAlimento";
        public static final String COLUMN_ID_EMPLEADO = "Orden_id_orden";
    }

    public static class TablaMesa {
        public static final String TABLE_NAME = "Mesa";
        public static final String COLUMN_ID_MESA = "idMesa";
        public static final String COLUMN_CANTIDAD = "numero_sillas";
        public static final String COLUMN_DISPONIBLE = "disponible";
        public static final String COLUMN_ID_SUCURSAL = "Sucursal_idSucursal";
    }

    //Nota: las tablas faltantes 'Empresa' y 'Sucursal' no son necesarias dentro del ámbito del proyecto
}
