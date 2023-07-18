package com.joguerrero.jhonny_bd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.joguerrero.jhonny_bd.Models.Marca;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    //attrib
    Button btnListar, btnListarMarcas;
    List<Marca> miListaMarcas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //relaciono los atributos con la vista
        btnListar = findViewById(R.id.btnListArticulos);
        btnListarMarcas = findViewById(R.id.btnListarMarcas);

        //llenar la lista


        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ArticulosActivity.class);
                startActivity(intent);
            }
        });

        btnListarMarcas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MarcaListaActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}