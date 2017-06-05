package com.nieves.fernando.saulshop;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import java.util.ArrayList;

public class ClientesActivity2 extends AppCompatActivity implements View.OnClickListener{
    ArrayList<Cliente> clientes;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "db_saulshop.db";
    public static final int REGISTRO_CLIENTE = 600;
    public Cliente nuevo_cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton btn_floating_registro = (FloatingActionButton)findViewById(R.id.fab_registro);
        btn_floating_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientesActivity2.this,RegistrarCliente.class);
                startActivityForResult(intent,REGISTRO_CLIENTE);
            }
        });

        clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente("Fernando Nieves Arauz","6461292853","fernando.nieves@uabc.edu.mx"));
        clientes.add(new Cliente("Francisco Pizon Martinez","6461721582","a335050@uabc.edu.mx"));
        clientes.add(new Cliente("Allan Yudiel Salazar Garcia","6461409500","allan.yudiel.salazar.garcia@uabc.edu.mx"));
        clientes.add(new Cliente("Miguel Angel Moreno Borja","6461254963","miguel.moreno8@uabc.edu.mx"));

        ArrayList<String> nombreContactos = new ArrayList<String>();

        for (Cliente nuevo_cliente: clientes)
        {
            nombreContactos.add(nuevo_cliente.getNombre());
        }

        //RecyclerView rv = (RecyclerView)findViewById(R.id.rv2);

        ListView lista_contactos = (ListView)findViewById(R.id.lv_clientes);
        lista_contactos.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nombreContactos));

        /*LinearLayoutManager llm = new LinearLayoutManager(ClientesActivity2.this);
        rv.setLayoutManager(llm);

        AdaptadorCliente adapter = new AdaptadorCliente(clientes);
        rv.setAdapter(adapter);*/

        lista_contactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ClientesActivity2.this,ActivityDetalleCliente.class);
                intent.putExtra(getResources().getString(R.string.txt_nombre_cliente),clientes.get(position).getNombre());
                intent.putExtra(getResources().getString(R.string.txt_telefono_cliente),clientes.get(position).getTelefono());
                intent.putExtra(getResources().getString(R.string.txt_correo_cliente),clientes.get(position).getEmail());
                startActivity(intent);
            }
        });
        /*rv.addOnItemTouchListener(new Recycler(this, rv, new Recycler.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(ClientesActivity2.this,ActivityDetalleCliente.class);
                intent.putExtra(getResources().getString(R.string.txt_nombre_cliente),clientes.get(position).getNombre());
                intent.putExtra(getResources().getString(R.string.txt_telefono_cliente),clientes.get(position).getTelefono());
                intent.putExtra(getResources().getString(R.string.txt_correo_cliente),clientes.get(position).getEmail());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(ClientesActivity2.this,"Que tratas de hacer?",Toast.LENGTH_LONG).show();
            }
        }));*/
    }

    public void onClick(View v) {

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
                case 600:
                    String n_nombre=data.getExtras().getString("cli_nombre");
                    String n_tel=data.getExtras().getString("cli_tel");
                    String n_email=data.getExtras().getString("cli_email");

                    //Bitmap imagen_cliente = new Bitmap((Bitmap)data.getExtras().getSerializable("cli_imagen"));

                    nuevo_cliente = new Cliente(n_nombre,n_tel,n_email);
                    clientes.add(nuevo_cliente);
                    finish();
                    startActivity(getIntent());
                    break;
            }
        }

    }
}
