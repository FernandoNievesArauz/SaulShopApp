package com.nieves.fernando.saulshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ClientesActivity2 extends AppCompatActivity implements View.OnClickListener{
    ArrayList<Cliente> clientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton btn_floating_scanner = (FloatingActionButton)findViewById(R.id.fab_registro);
        btn_floating_scanner.setOnClickListener(this);

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

        ListView lista_contactos = (ListView)findViewById(R.id.lv_clientes);
        lista_contactos.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nombreContactos));

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
    }

    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.fab_registro:
                Intent intent = new Intent(this,RegistrarCliente.class);
                startActivity(intent);
                break;

        }
    }
}
