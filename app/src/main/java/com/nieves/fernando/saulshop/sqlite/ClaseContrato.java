package com.nieves.fernando.saulshop.sqlite;

import android.provider.BaseColumns;

/**
 * Created by usuario on 04/06/2017.
 */

public class ClaseContrato {

    public static abstract class TablaCatalogo implements BaseColumns {
        public static final String TABLE_NAME = "catalogo";

        public static final String ID_PRODUCTO = "id";
        public static final String PRODUCTO_CODIGO = "producto_codigo";
        public static final String PRODUCTO_NOMBRE = "producto_nombre";
        public static final String PRODUCTO_DESCRIPCION = "producto_descripcion";
        public static final String PRODUCTO_PRECIO = "producto_precio";
        public static final String PRODUCTO_CANTIDAD_STOCK = "producto_cantidad_stock";
        public static final String PRODUCTO_FOTO = "producto_foto";
    }

    public static abstract class TablaClientes implements BaseColumns {
        public static final String TABLE_NAME = "clientes";

        public static final String ID_CLIENTE = "id";
        public static final String CLIENTE_NOMBRE = "cliente_nombre";
        public static final String CLIENTE_TELEFONO = "cliente_telefono";
        public static final String CLIENTE_EMAIL = "cliente_email";
        public static final String CLIENTE_FOTO = "cliente_foto";
    }


}
