package com.example.adrianch.eva2_12_permisos;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Principal extends AppCompatActivity {

    EditText edtTxt;
    Intent llamar;
    boolean permiso=false;
    final int PERMISO_LLAMAR=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        edtTxt = (EditText) findViewById(R.id.edtTxt);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            //PEDIR PERMISOS
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    PERMISO_LLAMAR);

        } else {
            permiso = true;
        }
    }

    public void enClick(View view) {
        if (permiso) {
            String telefono = "tel:"+edtTxt.getText().toString();
            llamar = new Intent(Intent.ACTION_CALL, Uri.parse(telefono));
            startActivity(llamar);
        } else {
            Toast.makeText(this, "No tiene permisos la app", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISO_LLAMAR) {
            Toast.makeText(this, permissions[0], Toast.LENGTH_SHORT).show();
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permiso concedido", Toast.LENGTH_SHORT).show();
                permiso = true;
            } else {
                Toast.makeText(this, "Nel pastel", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
