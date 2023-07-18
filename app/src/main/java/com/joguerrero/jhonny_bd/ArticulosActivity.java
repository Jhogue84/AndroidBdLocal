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
import com.joguerrero.jhonny_bd.Models.Articulo;

import java.util.List;

public class ArticulosActivity extends AppCompatActivity {

    FloatingActionButton btnAddArt;
    ListView listView;

    List<Articulo> listaArticulos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articulos);

        listView = findViewById(R.id.listView);

        btnAddArt = findViewById(R.id.btnAddArt);
        btnAddArt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(ArticulosActivity.this, "Ir al formulario adicionar", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ArticulosActivity.this, ArticuloFormActivity.class);
                intent.putExtra("accion", "Adicionar");
                intent.putExtra("id", 0);
                startActivity(intent);

            }
        });

        //Crear la lista para
        listaArticulos = Articulo.listarArticulosObj(this, null, "nombre");
        //list view crear un adaptador

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaArticulos);

        listView.setAdapter(arrayAdapter);
        //un clik sobre el elemento
        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(ArticulosActivity.this, "un click info del articulo", Toast.LENGTH_SHORT).show();
                irActivity(i);
            }
        });

         */

        //click sostenido
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                Articulo articulo = (Articulo) adapterView.getItemAtPosition(posicion);
                //Toast.makeText(ArticulosActivity.this, "ada "+articulo.getId(), Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(ArticulosActivity.this);
                builder.setTitle("Accion a realizar");
                String [] opcion = {"Modificar", "Eliminar", "Cancelar"};
                builder.setItems(opcion, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(opcion[i].equals("Modificar")){
                            finish();
                            Intent intent = new Intent(ArticulosActivity.this, ArticuloFormActivity.class);
                            intent.putExtra("accion", "Modificar");
                            intent.putExtra("id", articulo.getId());
                            startActivity(intent);
                        } else if (opcion[i].equals("Eliminar")) {
                            Articulo art = (Articulo) adapterView.getItemAtPosition(posicion);
                            eliminar(view, art.getId());
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Cancelar", Toast.LENGTH_SHORT).show();
                            dialogInterface.cancel();
                        }

                    }
                });
                builder.create().show();
                return false;
            }
        });

    }

    private void eliminar(View view, int id) {
        Snackbar.make(view, "¿ Eliminar ? ",Snackbar.LENGTH_LONG ).setAction("Aceptar", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Articulo articulo = new Articulo();
                articulo.eliminar(ArticulosActivity.this, id);
                Toast.makeText(ArticulosActivity.this, " !!! Se elimino el articulo ¡¡¡", Toast.LENGTH_SHORT).show();
                startActivity(getIntent());
            }
        }).show();
    }

}