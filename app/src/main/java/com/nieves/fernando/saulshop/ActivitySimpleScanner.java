package com.nieves.fernando.saulshop;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.content.ContentValues.TAG;

public class ActivitySimpleScanner extends Activity implements ZBarScannerView.ResultHandler, View.OnClickListener {
    private ZBarScannerView mScannerView;
    private String codigoe;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZBarScannerView(this);    // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        /*AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Resultado del escaneo:");
        builder.setMessage(rawResult.getContents());
        AlertDialog alerta = builder.create();
        alerta.show();*/

        codigoe=rawResult.getContents();

        Intent i = getIntent();
        // Le metemos el resultado que queremos mandar a la
        // actividad principal.
        i.putExtra("RESULTADO", codigoe);
        setResult(RESULT_OK, i);
        finish();

    }

    @Override
    public void onClick(View v) {

    }
}
