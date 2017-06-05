package com.nieves.fernando.saulshop.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Blob;
import java.util.UUID;

import static android.R.attr.id;

/**
 * Created by usuario on 03/06/2017.
 */

public class BasedeDatos extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "db_saulshop.db";

   /* private static final String sql_creartabla_catalogo = "CREATE TABLE db_catalogo (\n" +
            "db_id INTEGER PRIMARY KEY," +
            "db_id_producto VARCHAR(25)," +
            "db_nombre_producto VARCHAR(50) NOT NULL," +
            "db_descripcion_producto VARCHAR(60) NOT NULL," +
            "db_precio INTEGER," +
            "db_cantidad_stock INTEGER);";


    private static final String sql_creartabla_clientes = "CREATE TABLE db_clientes (" +
            "db_id_cliente INTEGER PRIMARY KEY AUTO_INCREMENT," +
            "db_nombre_cliente VARCHAR(50) NOT NULL," +
            "db_telefono VARCHAR(12) NOT NULL," +
            "db_email VARCHAR(50) NOT NULL," +
            "db_foto VARCHAR(1024);";*/

    public BasedeDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + ClaseContrato.TablaCatalogo.TABLE_NAME + "(" +
                ClaseContrato.TablaCatalogo.ID_PRODUCTO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ClaseContrato.TablaCatalogo.PRODUCTO_CODIGO + " VARCHAR(30) NOT NULL ," +
                ClaseContrato.TablaCatalogo.PRODUCTO_NOMBRE + " VARCHAR(60) NOT NULL ," +
                ClaseContrato.TablaCatalogo.PRODUCTO_DESCRIPCION + " VARCHAR(255) NOT NULL, " +
                ClaseContrato.TablaCatalogo.PRODUCTO_PRECIO + " VARCHAR(10) NOT NULL," +
                ClaseContrato.TablaCatalogo.PRODUCTO_CANTIDAD_STOCK + " VARCHAR(10) NOT NULL ," +
                "UNIQUE(" + ClaseContrato.TablaCatalogo.ID_PRODUCTO + "))");

        db.execSQL("CREATE TABLE " + ClaseContrato.TablaClientes.TABLE_NAME + "(" +
                ClaseContrato.TablaClientes.ID_CLIENTE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ClaseContrato.TablaClientes.CLIENTE_NOMBRE + " VARCHAR(30) NOT NULL ," +
                ClaseContrato.TablaClientes.CLIENTE_TELEFONO + " VARCHAR(60) NOT NULL ," +
                ClaseContrato.TablaClientes.CLIENTE_EMAIL + " VARCHAR(255) NOT NULL, " +
                ClaseContrato.TablaClientes.CLIENTE_FOTO + " VARCHAR(10) NOT NULL," +
                "UNIQUE(" + ClaseContrato.TablaClientes.ID_CLIENTE + "))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long agregarProducto(SQLiteDatabase db, String temp_prod_codigo, String temp_prod_nombre, String temp_prod_descripcion, String temp_prod_precio, String temp_prod_cantidad_stock){
        ContentValues valores = new ContentValues();
        valores.put(ClaseContrato.TablaCatalogo.ID_PRODUCTO, UUID.randomUUID().toString());
        valores.put(ClaseContrato.TablaCatalogo.PRODUCTO_CODIGO, temp_prod_codigo);
        valores.put(ClaseContrato.TablaCatalogo.PRODUCTO_NOMBRE, temp_prod_nombre);
        valores.put(ClaseContrato.TablaCatalogo.PRODUCTO_DESCRIPCION, temp_prod_descripcion);
        valores.put(ClaseContrato.TablaCatalogo.PRODUCTO_PRECIO, temp_prod_precio);
        valores.put(ClaseContrato.TablaCatalogo.PRODUCTO_CANTIDAD_STOCK, temp_prod_cantidad_stock);

        return db.insert(
                ClaseContrato.TablaCatalogo.TABLE_NAME,
                null,
                valores
        );


    }
}
