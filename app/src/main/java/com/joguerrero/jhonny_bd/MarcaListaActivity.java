package com.joguerrero.jhonny_bd;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.joguerrero.jhonny_bd.Models.Marca;

import java.util.List;

public class MarcaListaActivity extends AppCompatActivity {

    List<Marca> listaMarcas;
    ListView lvMarcas;
    FloatingActionButton btnAddMarca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marca_lista);

        lvMarcas = findViewById(R.id.lvMarcas);
        btnAddMarca =findViewById(R.id.btnAddMarca);

        listaMarcas = Marca.listarMarcasObj(this, null, "nombre");

        ArrayAdapter arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaMarcas);
        lvMarcas.setAdapter(arrayAdapter);

        lvMarcas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Marca marca = (Marca) adapterView.getItemAtPosition(i);
                mostrarOpciones(marca.getId(), view);
            }
        });

        //boton flotante
        btnAddMarca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MarcaListaActivity.this, MarcaFormularioActivity.class);
                intent.putExtra("accion", "Adicionar");
                intent.putExtra("id",0);
                startActivity(intent);
                finish();
            }
        });

    }

    private void mostrarOpciones(int id, View view) {
        String [] opciones ={"Ver Detalle", "Modificar", "Eliminar", "Cancelar"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccione una opcion:");
        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(opciones[i].equals("Ver Detalle")){
                    verDetalle(id);
                }
                if(opciones[i].equals("Modificar")){
                    modificar(id);
                }
                if(opciones[i].equals("Eliminar")){
                    eliminar(id, view);
                }
                if(opciones[i].equals("Cancelar")){
                    dialogInterface.cancel();
                }
            }
        });
        builder.show();
    }

    private void eliminar(int id, View view) {
        Snackbar.make(view, "¿ Desea eliminar el item ?", Snackbar.LENGTH_LONG).setAction("Aceptar", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Marca marca = new Marca(id, MarcaListaActivity.this);
                marca.eliminar(MarcaListaActivity.this, id);
                Toast.makeText(MarcaListaActivity.this, " !! Se elimino el item exitosamente ¡¡ ", Toast.LENGTH_SHORT).show();
                startActivity(getIntent());
            }
        }).show();
    }

    private void modificar(int id) {
        Intent intent = new Intent(this, MarcaFormularioActivity.class);
        intent.putExtra("accion", "Modificar");
        intent.putExtra("id", id);
        startActivity(intent);
        finish();
    }

    private void verDetalle(int id) {
        Intent intent = new Intent(this, MarcaDetActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
        finish();
    }
}