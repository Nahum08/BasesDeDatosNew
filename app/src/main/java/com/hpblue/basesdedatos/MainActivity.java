package com.hpblue.basesdedatos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.Currency;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    public Contacto contactoParaEliminar,contactoSelect;

    Cursor cs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DaoContacto dao = new DaoContacto(this);

         cs = dao.getAllCursor();
            lv = findViewById(R.id.lstview);
        SimpleCursorAdapter adp = new SimpleCursorAdapter(
                this,android.R.layout.simple_list_item_2, cs,new String[]{"usuario","email"},
                new int[]{android.R.id.text1,android.R.id.text2},SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE
        );
        lv.setAdapter(adp);
        onActivityResult(1000,-1,getIntent());
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                List<Contacto> importar = dao.getAll();
                contactoParaEliminar = importar.get(i);

                AlertDialog dialog = new AlertDialog
                        .Builder(MainActivity.this)
                        .setPositiveButton("Sí, eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String x  = contactoParaEliminar.id+"";
                                int y = dao.delete(x);
                                if (y>0){
                                    Toast.makeText(MainActivity.this, "exito", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                                }
                                onActivityResult(1000,-1,getIntent());

                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setTitle("Confirmar")
                        .setMessage(" " + contactoParaEliminar.getUsuario() + "?")
                        .create();
                dialog.show();
                return true;

            }

        });


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                List<Contacto> importar = dao.getAll();
                contactoSelect = importar.get(i);

                Intent intent
                        = new Intent(MainActivity.this, Edita_contacto.class);
                intent.putExtra("_id", contactoSelect.getId());
                intent.putExtra("usuario", contactoSelect.getUsuario());
                intent.putExtra("email", contactoSelect.getEmail());
                intent.putExtra("telefono", contactoSelect.getTelefono());
                intent.putExtra("fecha_nacimiento", contactoSelect.getFecha_nacimiento());
                startActivityForResult(intent, 1000);
            }
        });
    }


    public void click_aceptar(View view) {
        Intent intent = new Intent(MainActivity.this,agregar.class);
        startActivityForResult(intent,1000);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == RESULT_OK) {
            SimpleCursorAdapter adp = new SimpleCursorAdapter(
                    this,
                    android.R.layout.simple_expandable_list_item_2,
                    cs,
                    new String[]{"usuario", "email"},
                    new int[]{android.R.id.text1, android.R.id.text2},
                    SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE
            );
            lv.setAdapter(adp);
        }
    }

}
