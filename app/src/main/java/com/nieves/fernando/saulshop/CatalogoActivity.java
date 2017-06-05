package com.nieves.fernando.saulshop;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.nieves.fernando.saulshop.sqlite.BasedeDatos;
import com.nieves.fernando.saulshop.sqlite.ClaseContrato;

import java.util.ArrayList;

import static android.R.attr.data;


public class CatalogoActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<Producto> catalogo;

    BasedeDatos base_de_datos;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "db_saulshop.db";
    public static final int REGISTRO_PRODUCTO = 500;
    public Producto producto_nuevo;
    MiAdaptador array_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        producto_nuevo = new Producto("1285479652","Pantalon Diesel Negro","Pantalon Negro de excelente calidad, extrema comodidad","$550","5");

        catalogo = new ArrayList<Producto>();
        catalogo.add(producto_nuevo);
        catalogo.add(new Producto("1285476363","Paquete Calcetines Hanes","Calcetines de algodon, delgados y con diseños formales","$120","9"));

        FloatingActionButton btn_floating_scanner = (FloatingActionButton)findViewById(R.id.fab_scanner);
        btn_floating_scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CatalogoActivity.this,RegistrarProducto.class);
                startActivityForResult(intent,REGISTRO_PRODUCTO);
            }
        });

        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);

        ArrayList<String> nombreProducto = new ArrayList<String>();
        for (Producto nuevo_producto: catalogo)
        {
            nombreProducto.add(nuevo_producto.getNombre_producto());
        }

        LinearLayoutManager llm = new LinearLayoutManager(CatalogoActivity.this);
        rv.setLayoutManager(llm);

        MiAdaptador adapter = new MiAdaptador(catalogo);
        rv.setAdapter(adapter);

 /*       rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CatalogoActivity.this,ActivityDetallesProducto.class);

                intent.putExtra(getResources().getString(R.string.txt_id_producto),catalogo.get(position).getCodigo_producto());
                intent.putExtra(getResources().getString(R.string.txt_nombre_producto),catalogo.get(position).getNombre_producto());
                intent.putExtra(getResources().getString(R.string.txt_descripcion_producto),catalogo.get(position).getDescripcion_producto());
                intent.putExtra(getResources().getString(R.string.txt_precio_producto),catalogo.get(position).getPrecio()).toString();
                intent.putExtra(getResources().getString(R.string.txt_cantidad_stock_producto),catalogo.get(position).getCantidad_stock()).toString();

                startActivity(intent);
            }
        });*/

        rv.addOnItemTouchListener(new Recycler(this, rv, new Recycler.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(CatalogoActivity.this,ActivityDetallesProducto.class);

                intent.putExtra(getResources().getString(R.string.txt_id_producto),catalogo.get(position).getCodigo_producto());
                intent.putExtra(getResources().getString(R.string.txt_nombre_producto),catalogo.get(position).getNombre_producto());
                intent.putExtra(getResources().getString(R.string.txt_descripcion_producto),catalogo.get(position).getDescripcion_producto());
                intent.putExtra(getResources().getString(R.string.txt_precio_producto),catalogo.get(position).getPrecio()).toString();
                intent.putExtra(getResources().getString(R.string.txt_cantidad_stock_producto),catalogo.get(position).getCantidad_stock()).toString();

                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(CatalogoActivity.this,"Que tratas de hacer?",Toast.LENGTH_LONG).show();
            }
        }));


        /*ListView lista_catalogo = (ListView)findViewById(R.id.lv_productos);
        lista_catalogo.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nombreProducto));

        rv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CatalogoActivity.this,ActivityDetallesProducto.class);

                intent.putExtra(getResources().getString(R.string.txt_id_producto),catalogo.get(position).getCodigo_producto());
                intent.putExtra(getResources().getString(R.string.txt_nombre_producto),catalogo.get(position).getNombre_producto());
                intent.putExtra(getResources().getString(R.string.txt_descripcion_producto),catalogo.get(position).getDescripcion_producto());
                intent.putExtra(getResources().getString(R.string.txt_precio_producto),catalogo.get(position).getPrecio()).toString();
                intent.putExtra(getResources().getString(R.string.txt_cantidad_stock_producto),catalogo.get(position).getCantidad_stock()).toString();

                startActivity(intent);
            }
        });*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Registro Cancelado", Toast.LENGTH_SHORT)
                    .show();
        } else {
            switch (resultCode)
            {
                case 500:
                    String n_codigo=data.getExtras().getString("prod_codigoe");
                    String n_nombre=data.getExtras().getString("prod_nombre");
                    String n_descrip=data.getExtras().getString("prod_descripcion");
                    String n_precio=data.getExtras().getString("prod_precio");
                    String n_cantidad=data.getExtras().getString("prod_cantidad_stock");

                    Toast.makeText(this,n_codigo+n_nombre+n_descrip+n_precio+n_cantidad,Toast.LENGTH_LONG).show();

                    producto_nuevo = new Producto(n_codigo,n_nombre,n_descrip,n_precio,n_cantidad);
                    catalogo.add(producto_nuevo);
                    finish();
                    startActivity(getIntent());
                    break;
            }
        }

    }



    @Override
    public void onClick(View v) {

    }
}
