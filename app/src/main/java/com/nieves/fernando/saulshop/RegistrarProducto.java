package com.nieves.fernando.saulshop;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.nieves.fernando.saulshop.sqlite.BasedeDatos;

public class RegistrarProducto extends AppCompatActivity{

    public static final String TABLA_CATALOGO = "db_catalogo";
    public static final int CODIGO_CATALOGO = 200;
    public static final int CODIGO_CLIENTE = 300;
    private static final int CODIGO_ESCANER = 400;

    TextView et_codigobarras;
    String codigo_escaneado;
    Button btn_registrar_producto;
    public SQLiteDatabase base_de_datos;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "db_saulshop.db";

    TextView tv_regprod_id_producto;
    EditText et_regprod_nombre;
    EditText et_regprod_descripcion;
    EditText et_regprod_precio;
    EditText et_regprod_cantidad_stock;

    String valor_id_producto;
    String valor_nombre;
    String valor_descripcion;
    String valor_precio;
    String valor_cantidad_stock;

    int true_valor_precio=0;
    int true_valor_cantidad_stock=0;

    public Producto temp_prod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_producto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tv_regprod_id_producto = (TextView)findViewById(R.id.tv_registro_idproducto);
        et_regprod_nombre = (EditText)findViewById(R.id.et_producto_registro_nombre);
        et_regprod_descripcion = (EditText)findViewById(R.id.et_producto_registro_descripcion);
        et_regprod_precio = (EditText)findViewById(R.id.et_producto_registro_precio);
        et_regprod_cantidad_stock = (EditText)findViewById(R.id.et_producto_registro_cantidadstock);

        btn_registrar_producto = (Button)findViewById(R.id.btn_producto_registrar);

        btn_registrar_producto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valor_id_producto = tv_regprod_id_producto.getText().toString();
                valor_nombre = et_regprod_nombre.getText().toString();
                valor_descripcion = et_regprod_descripcion.getText().toString();
                valor_precio = et_regprod_precio.getText().toString();
                valor_cantidad_stock = et_regprod_cantidad_stock.getText().toString();

                Intent i = getIntent();

                i.putExtra("prod_codigoe", valor_id_producto);
                i.putExtra("prod_nombre",valor_nombre);
                i.putExtra("prod_descrip",valor_descripcion);
                i.putExtra("prod_precio",valor_precio);
                i.putExtra("prod_stock",valor_cantidad_stock);

                setResult(500, i);
                finish();

            }
        });

        Producto pruebaProducto = new Producto("1285479652","Pantalon Diesel Negro","Pantalon Negro de excelente calidad, extrema comodidad","$550","5");

    }


    protected void onPause() {
        super.onPause();

    }

    public void escanearCodigo(View v)
    {
        Intent i = new Intent(this, ActivitySimpleScanner.class);
        startActivityForResult(i,1);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Accion Cancelada", Toast.LENGTH_SHORT)
                    .show();
        } else {
            codigo_escaneado = data.getExtras().getString("RESULTADO");
            tv_regprod_id_producto.setText(codigo_escaneado);
        }
    }

    public void agregarProducto(String id_producto, String nombre, String descripcion, int precio, int cantidad_stock){

        BasedeDatos catalogo = new BasedeDatos(this,"db_catalogo",null,1);
        SQLiteDatabase db = catalogo.getWritableDatabase();

        ContentValues valores = new ContentValues();

        valores.put("db_id_producto", id_producto);
        valores.put("db_nombre", nombre);
        valores.put("db_descripcion", descripcion);
        valores.put("db_precio", precio);
        valores.put("db_cantidad_stock", cantidad_stock);

        db.insert(TABLA_CATALOGO, null,valores);

        db.close();
    }
/*
    private void nuevoRegistro(SQLiteDatabase db,Producto producto_nuevo) {
        base_de_datos.agregarProducto(db, producto_nuevo.getCodigo_producto(),producto_nuevo.getNombre_producto(),producto_nuevo.getDescripcion_producto()
                ,producto_nuevo.getPrecio(),producto_nuevo.getCantidad_stock());

    }*/
}