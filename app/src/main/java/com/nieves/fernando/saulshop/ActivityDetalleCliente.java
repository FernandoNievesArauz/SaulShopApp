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

public class ActivityDetalleCliente extends AppCompatActivity {
    private TextView tv_nombre_cliente;
    private TextView tv_telefono_cliente;
    private TextView tv_correo_cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle parametros = getIntent().getExtras();

        String nombre = parametros.getString(getResources().getString(R.string.txt_nombre_cliente));
        String telefono = parametros.getString(getResources().getString(R.string.txt_telefono_cliente));
        String correo = parametros.getString(getResources().getString(R.string.txt_correo_cliente));

        tv_nombre_cliente = (TextView) findViewById(R.id.tv_nombre);
        tv_telefono_cliente = (TextView) findViewById(R.id.tv_telefono);
        tv_correo_cliente = (TextView) findViewById(R.id.tv_correo);

        tv_nombre_cliente.setText(nombre);
        tv_telefono_cliente.setText(telefono);
        tv_correo_cliente.setText(correo);
    }

    public void llamar(View v) {

        String telefono = tv_telefono_cliente.getText().toString();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + telefono)));
    }

    public void correo(View v)
    {
        String mail = tv_correo_cliente.getText().toString();
        Intent emailintent = new Intent((Intent.ACTION_SEND));
        emailintent.setData(Uri.parse("Mailto: "));
        emailintent.putExtra(Intent.EXTRA_EMAIL,mail);
        emailintent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailintent,"Email"));
    }

}
