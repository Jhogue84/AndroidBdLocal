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
        btnAccion = findViewById(R.id.btnListarMarcas);
        btnCancelarMar = findViewById(R.id.btnCancelarMar);
        //
        Bundle bundle = getIntent().getExtras();
        String accion = bundle.getString("accion");
        int id = bundle.getInt("id");
        tvTitulo.setText("Formulario "+accion+" Marca");
        btnAccion.setText(accion);
        //validar la accion a realizar
        validarAccion(id, accion);

        btnAccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (accion.equals("Adicionar")){
                    validarCamposVacios(id);
                }else{
                    validarCamposVacios(id);
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

    private void validarCamposVacios(int id) {
        if(etNombre.getText().toString().isEmpty()){
            etNombre.setError("Nombre Marca es obligatorio");
        }
        else{
            if(id == 0){
                adicionaMarca();
            }
            else{
                modificarMarca(id);
            }
        }

    }

    private void validarAccion(int id, String accion) {
        if (accion.equals("Modificar")){
            Marca marca = new Marca(id, this);
            tvId.setText("ID: "+id);
            etNombre.setText(marca.getNombre());
            etDescripcion.setText(marca.getDescripcion());
        }
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
        Intent intent = new Intent(this, MarcaListaActivity.class);
        startActivity(intent);
    }

    private void modificarMarca(int idBd){
        //recuperar valorse del formulario
        String nombre = etNombre.getText().toString();
        String descripcion = etDescripcion.getText().toString();
        //crear el objeto tipo marca para usar el metodo insertar.
        Marca miMarca = new Marca();
        miMarca.setNombre(nombre);
        miMarca.setDescripcion(descripcion);
        miMarca.modificar(this, idBd);
        Toast.makeText(this, "Se modifico exitosamente", Toast.LENGTH_SHORT).show();
        finish();
        Intent intent = new Intent(this, MarcaListaActivity.class);
        startActivity(intent);
    }

}