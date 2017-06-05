package com.nieves.fernando.saulshop;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ActivityDetallesProducto extends AppCompatActivity {

    private TextView tv_id_producto_l;
    private TextView tv_nombre_producto_l;
    private TextView tv_descripcion_producto_l;
    private TextView tv_precio_producto_l;
    private TextView tv_cantidad_stock_producto_l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_producto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle parametros = getIntent().getExtras();

        String id_producto = parametros.getString(getResources().getString(R.string.txt_id_producto));
        String nombre_producto = parametros.getString(getResources().getString(R.string.txt_nombre_producto));
        String descripcion_producto = parametros.getString(getResources().getString(R.string.txt_descripcion_producto));
        String precio_producto = parametros.getString(getResources().getString(R.string.txt_precio_producto));
        String cantidad_stock_producto = parametros.getString(getResources().getString(R.string.txt_cantidad_stock_producto));

        tv_id_producto_l = (TextView) findViewById(R.id.tv_id_producto);
        tv_nombre_producto_l = (TextView) findViewById(R.id.tv_nombre_producto);
        tv_descripcion_producto_l = (TextView) findViewById(R.id.tv_descripcion_producto);
        tv_precio_producto_l = (TextView) findViewById(R.id.tv_precio_producto);
        tv_cantidad_stock_producto_l = (TextView) findViewById(R.id.tv_cantidad_stock_producto);

        tv_id_producto_l.setText(id_producto);
        tv_nombre_producto_l.setText(nombre_producto);
        tv_descripcion_producto_l.setText(descripcion_producto);
        tv_precio_producto_l.setText(precio_producto);
        tv_cantidad_stock_producto_l.setText(cantidad_stock_producto);

    }

}
