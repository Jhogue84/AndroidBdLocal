package com.joguerrero.jhonny_bd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.joguerrero.jhonny_bd.Models.Articulo;
import com.joguerrero.jhonny_bd.Models.Marca;

import java.util.ArrayList;
import java.util.List;

public class ArticuloFormActivity extends AppCompatActivity implements View.OnClickListener {

    String accion;
    int idArticulo;
    TextView tvTtituloArt, tvIdArt;
    EditText etNombreArt, etPrecioArt;
    Button btnAccionArt, btnCancelarArt;
    Spinner spinner;
    int posicion;
    List<Marca> miListaMarcas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articulo_form);

        tvTtituloArt = findViewById(R.id.tvTituloArt);
        tvIdArt = findViewById(R.id.tvIdArt);
        etNombreArt = findViewById(R.id.etNombreArt);
        etPrecioArt = findViewById(R.id.etPrecioArt);
        btnAccionArt = findViewById(R.id.btnAccionArt);
        btnCancelarArt = findViewById(R.id.btnCancelarArt);
        spinner = findViewById(R.id.spinner);
        btnAccionArt.setOnClickListener(this);
        btnCancelarArt.setOnClickListener(this);


        Bundle bundle = getIntent().getExtras();
        accion = bundle.getString("accion");
        idArticulo = bundle.getInt("id");

        cargarListaMarcas(accion);

        tvTtituloArt.setText("Formulario de "+accion);
        btnAccionArt.setText(accion);
        validarAccion(accion, idArticulo);
    }

    private void validarAccion(String accion, int idArticulo) {
        if (accion.equals("Modificar")){
            Articulo articulo = new Articulo(idArticulo, this);
            tvIdArt.setText(""+idArticulo);
            etNombreArt.setText(articulo.getNombre());
            etPrecioArt.setText(""+(int) articulo.getPrecio());
        }
    }

    private void cargarListaMarcas(String accion) {
        miListaMarcas = Marca.listarMarcasObj(this, null, "nombre");
        //crear el adaptador para el spinner
        ArrayAdapter miAdaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, miListaMarcas);
        //colocamo el adaptador en el spinner
        spinner.setAdapter(miAdaptador);
        if (accion.equals("Modificar")) {
            Articulo art = new Articulo(idArticulo, this);
            int idMarca = art.getIdMarca();
            for (int i = 0; i < miAdaptador.getCount(); i++) {
                Marca marca = ((Marca) spinner.getItemAtPosition(i));
                if (marca.getId() == idMarca) {
                    posicion = i;
                    //Log.e("iguales  ", ""+marca.getId()+" -> "+idMarca+" sel "+posicion);
                }
            }
            spinner.setSelection(posicion);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAccionArt:
                if(accion.equals("Adicionar")){
                    validarCamposVacios(0);
                }else{
                    validarCamposVacios(idArticulo);
                }
                break;
            case R.id.btnCancelarArt:
                Intent intent = new Intent(ArticuloFormActivity.this, ArticulosActivity.class);
                startActivity(intent);
                break;
        }


    }

    private void validarCamposVacios(int idArticulo) {
        String error= null;
        if (etNombreArt.getText().toString().isEmpty()){
            etNombreArt.setError("Campo obligatorio");
            error +="1";
        }
        if (etPrecioArt.getText().toString().isEmpty()){
            etPrecioArt.setError("El valor es obligatorio");
            error +="2";
        }
        if (spinner.getSelectedItemPosition() == 0) {
            // set error message on spinner
            TextView errorSpn = (TextView) spinner.getSelectedView();
            errorSpn.setError("Debe seleccionar la marca");
            error +="3";
        }
        if (error == null){
            if (idArticulo == 0){
                adicionar();
                //Toast.makeText(this, "ir adicionar"+ idMarca, Toast.LENGTH_SHORT).show();
            }else {
                modificar(idArticulo);
            }

        }
    }

    private void modificar(int idAnterior) {
        Articulo articulo = new Articulo();
        articulo.setNombre(etNombreArt.getText().toString());
        articulo.setPrecio(Double.parseDouble(etPrecioArt.getText().toString()));
        //seleccionar el id del spinner
        Marca marca = (Marca) spinner.getSelectedItem();
        articulo.setIdMarca(marca.getId());
        articulo.modificar(this, idArticulo);
        Intent intent = new Intent(ArticuloFormActivity.this, ArticulosActivity.class);
        startActivity(intent);
        Toast.makeText(this, "!!! La Modificacion fue exitosa !!!", Toast.LENGTH_SHORT).show();
        finish();

    }

    private void adicionar() {
        Articulo articulo = new Articulo();
        articulo.setNombre(etNombreArt.getText().toString());
        articulo.setPrecio(Double.parseDouble(etPrecioArt.getText().toString()));
        //seleccionar el id del spinner
        Marca marca = (Marca) spinner.getSelectedItem();
        articulo.setIdMarca(marca.getId());
        articulo.insertar(this);
        Intent intent = new Intent(ArticuloFormActivity.this, ArticulosActivity.class);
        startActivity(intent);
        Toast.makeText(this, "!!! El registro fue exitoso !!!", Toast.LENGTH_SHORT).show();
        finish();
    }

}