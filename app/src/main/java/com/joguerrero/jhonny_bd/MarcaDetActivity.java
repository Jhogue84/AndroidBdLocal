package com.joguerrero.jhonny_bd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.joguerrero.jhonny_bd.Models.Marca;

public class MarcaDetActivity extends AppCompatActivity {

    EditText etDescripcionMar;
    TextView tvIdMar, tvNombreMar;

    Button btnVolverMarcas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marca_det);
        etDescripcionMar = findViewById(R.id.etDescripcionMar);
        tvIdMar = findViewById(R.id.tvIdMarca);
        tvNombreMar = findViewById(R.id.tvNombreMar);
        btnVolverMarcas = findViewById(R.id.btnVolverMar);

        Bundle bundle = getIntent().getExtras();

        Marca marca = new Marca(bundle.getInt("id"), this );
        tvIdMar.setText("ID Marca: "+marca.getId());
        tvNombreMar.setText(marca.getNombre().toString());
        etDescripcionMar.setText(marca.getDescripcion().toString());


        btnVolverMarcas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MarcaDetActivity.this, MarcaListaActivity.class);
                startActivity(intent);
            }
        });
    }


}