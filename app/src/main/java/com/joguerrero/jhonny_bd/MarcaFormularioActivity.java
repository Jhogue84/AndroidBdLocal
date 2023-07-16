package com.joguerrero.jhonny_bd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.joguerrero.jhonny_bd.Models.Marca;

public class MarcaFormularioActivity extends AppCompatActivity {

    //atributos
    TextView tvTitulo, tvId;
    EditText etNombre, etDescripcion;
    Button btnAccion,btnCancelarMar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marca_formulario);
        //realcionamos los atributos
        tvId = findViewById(R.id.tvId);
        tvTitulo = findViewById(R.id.tvTitulo);
        etNombre = findViewById(R.id.etNombre);
        etDescripcion = findViewById(R.id.etDescripcion);
        btnAccion = findViewById(R.id.btnAddMarca);
        btnCancelarMar = findViewById(R.id.btnCancelarMar);
        //
        Bundle bundle = getIntent().getExtras();
        String accion = bundle.getString("accion");
        tvTitulo.setText("Formulario de "+accion+" Marca");
        btnAccion.setText(accion);

        btnAccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (accion.equals("Adicionar")){
                    adicionaMarca();
                }else{
                   modificarMarca();
                }
            }
        });

        btnCancelarMar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MarcaFormularioActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }

    private void adicionaMarca() {
        //recuperar valorse del formulario
        String nombre = etNombre.getText().toString();
        String descripcion = etDescripcion.getText().toString();
        //crear el objeto tipo marca para usar el metodo insertar.
        Marca miMarca = new Marca();
        miMarca.setNombre(nombre);
        miMarca.setDescripcion(descripcion);
        miMarca.insertar(this);
        Toast.makeText(this, "Se adiciono exitosamente", Toast.LENGTH_SHORT).show();
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void modificarMarca(){
        Toast.makeText(this, "Metodo modificar", Toast.LENGTH_SHORT).show();
    }

}