package com.hpblue.basesdedatos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class agregar extends AppCompatActivity {
    EditText us,Em,tel,fec;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        us = findViewById(R.id.txtUsuario);
        Em = findViewById(R.id.txtEmail);
        tel = findViewById(R.id.txtTelefono);
        fec = findViewById(R.id.txtFecha);

    }

    public void click_aceptar(View view) {
        DaoContacto dao =  new DaoContacto(this);

        long res = dao.insert(new Contacto(0,us.getText().toString(),
                Em.getText().toString(),tel.getText().toString(),
                fec.getText().toString()
                ));

        if (res>0){
            Toast.makeText(this, "Adición exitosa",
                    Toast.LENGTH_LONG).show();
        }

        Intent intent =  new Intent(this,MainActivity.class);
        startActivityForResult(intent,1000);
    }

    public void click_cancel(View view) {

    }
}

