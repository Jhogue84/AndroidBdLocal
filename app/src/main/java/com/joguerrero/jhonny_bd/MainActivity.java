package com.joguerrero.jhonny_bd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


import com.joguerrero.jhonny_bd.Models.Marca;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    //attrib
    Button btnListar, btnAddMarca;
    List<Marca> miListaMarcas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //relaciono los atributos con la vista
        btnListar = findViewById(R.id.btnListArticulos);
        btnAddMarca = findViewById(R.id.btnAddMarca);

        //llenar la lista


        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ArticulosActivity.class);
                startActivity(intent);
            }
        });

        btnAddMarca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MarcaFormularioActivity.class);
                intent.putExtra("accion", "Adicionar");
                startActivity(intent);
            }
        });


    }
}