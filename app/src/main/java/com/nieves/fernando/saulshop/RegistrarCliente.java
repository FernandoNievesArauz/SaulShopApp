package com.nieves.fernando.saulshop;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import java.io.File;
import java.io.Serializable;

public class RegistrarCliente extends AppCompatActivity {
    private final int PHOTO_CODE = 100;
    private final int SELECT_PICTURE = 200;

    //Atributos a utilizar para las imagenes
    private String APP_DIRECTORIO = "clientes/";
    private String IMAGEN_DIRECTORIO = APP_DIRECTORIO + "fotos";
    private String NOMBRE_TEMPORAL = "temporal.jpg";

    //Variables para instanciar elemntos del XML
    private ImageView imagen_cliente;
    private Button btn_tomar_foto;
    private Button btn_galeria;
    private Button btn_registrar;


    EditText et_regcli_nombre;
    EditText et_regcli_tel;
    EditText et_regcli_email;

    String valor_nombre;
    String valor_tel;
    String valor_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imagen_cliente = (ImageView)findViewById(R.id.iv_fotografia_cliente);
        btn_tomar_foto = (Button)findViewById(R.id.btn_regcli_imagen_camara);
        btn_galeria = (Button)findViewById(R.id.btn_regcli_imagen_galeria);
        btn_registrar = (Button)findViewById(R.id.btn_regcli_registrar);

        btn_tomar_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCamara();
            }
        });

        btn_galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elegirImagen();
            }
        });

        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valor_nombre = et_regcli_nombre.getText().toString();
                valor_tel = et_regcli_tel.getText().toString();
                valor_email = et_regcli_email.getText().toString();

                Intent i = getIntent();

                i.putExtra("cli_nombre", valor_nombre);
                i.putExtra("cli_tel",valor_tel);
                i.putExtra("cli_email",valor_email);
                //i.putExtra("imagen", (Serializable) imagen_cliente);

                setResult(600, i);
                finish();

            }
        });


    }

    private void abrirCamara()
    {
        File imagen = new File(Environment.getExternalStorageDirectory(),IMAGEN_DIRECTORIO);
        String path = Environment.getExternalStorageDirectory()
                + File.separator+IMAGEN_DIRECTORIO+File.separator+NOMBRE_TEMPORAL;
        File imagen_final = new File(path);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imagen_final));
        startActivityForResult(intent,PHOTO_CODE);

    }

    private void elegirImagen()
    {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent.createChooser(intent,"Seleccionar fuente de imagen"),SELECT_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch (requestCode)
        {
            case PHOTO_CODE:
                String dir = Environment.getExternalStorageDirectory()
                        + File.separator+IMAGEN_DIRECTORIO+File.separator+NOMBRE_TEMPORAL;
                Bitmap imagen_buena = decodeBitmap(dir);
                imagen_cliente.setImageBitmap(imagen_buena);
                break;

            case SELECT_PICTURE:
                Uri path = data.getData();
                imagen_cliente.setImageURI(path);
                break;

            case RESULT_CANCELED:
                Toast.makeText(this,"Captura de Imagen Cancelada",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private Bitmap decodeBitmap(String direccion)
    {
        Bitmap bitmap;
        bitmap = BitmapFactory.decodeFile(direccion);
        return bitmap;
    }


}
