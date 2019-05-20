package com.example.restaurantproject.persistencia;

import android.provider.BaseColumns;

public final class RestaurantContract {

    private RestaurantContract() {
    }

    public static class TablaAlimento implements BaseColumns {
        public static final String TABLE_NAME = "Alimento";
        public static final String COLUMN_NOMBRE = "nombre";
        public static final String COLUMN_PRECIO = "precio";
        public static final String COLUMN_EXISTENCIA = "existencia";
    }


}
