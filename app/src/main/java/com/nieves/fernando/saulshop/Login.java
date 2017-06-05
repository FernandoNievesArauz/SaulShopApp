package com.nieves.fernando.saulshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btn_inicio = (Button)findViewById(R.id.btn_login);

        btn_inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_usuario = ((EditText)findViewById(R.id.et_usuario)).getText().toString();
                String str_password = ((EditText)findViewById(R.id.et_password)).getText().toString();

                if(str_usuario.equals("admin") && str_password.equals("admin"))
                {
                    Intent bienvenido = new Intent(Login.this,MainActivity.class);

                    startActivity(bienvenido);

                }else{
                    Toast.makeText(getApplicationContext(),"Usuario o Contraseña incorrectos",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
