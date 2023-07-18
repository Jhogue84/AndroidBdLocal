package com.joguerrero.jhonny_bd.Configuracion;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class BdOpenHelper extends SQLiteOpenHelper {
    //atributos -------------
    private static String bdNombre ="Productos";
    private static int bdVersion = 1;

    //metodos ----------------------
    public BdOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase miDb) {
        //se ejecutan cuando se instala la app, aqui se crea la BD.
        String cadenaSql = "create table marcas (id integer primary key autoincrement, nombre text, descripcion text)";
        miDb.execSQL(cadenaSql);
        //otra tabla
        cadenaSql = "create table articulos (id integer primary key autoincrement, nombre text, precio double, idMarca references marcas(id)) on delete no action on update cascade";
        miDb.execSQL(cadenaSql);
        //insertar marcas y articulos - NO recomendable
        cadenaSql ="insert into marcas (nombre, descripcion) values ('Dell copia','pc de escritorio')";
        miDb.execSQL(cadenaSql);
        cadenaSql ="insert into marcas (nombre, descripcion) values ('HP copia','pc de escritorio xyz')";
        miDb.execSQL(cadenaSql);
        cadenaSql ="insert into articulos (nombre, precio, idmarca) values ('Copia Computardora Desktop', 2500000.0, 1)";
        miDb.execSQL(cadenaSql);

        cadenaSql ="insert into articulos (nombre, precio, idmarca) values ('Copia Computardora Escritorio', 2500000.0, 1)";
        miDb.execSQL(cadenaSql);
        cadenaSql ="insert into articulos (nombre, precio, idmarca) values ('Copia Computardora Portatil', 3500000.0, 1)";
        miDb.execSQL(cadenaSql);
        cadenaSql ="insert into articulos (nombre, precio, idmarca) values ('Copia Parlante Portatil', 350000.0, 2)";
        miDb.execSQL(cadenaSql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {//sqliteDatabase
        //Actualizacion de la base de datos
        String cadenaSql ="drop table if exists marcas";
        db.execSQL(cadenaSql);
        cadenaSql ="drop table if exists articulos";
        db.execSQL(cadenaSql);
        //llamar al metodo de crear
        onCreate (db);
    }
    //crear mi metodos, genericos
    // =
    public static void consultaSinRetorno(Context context,String cadenaSql){
        //delete - insert - update
        BdOpenHelper bdConector = new BdOpenHelper(context, bdNombre,null, bdVersion);
        SQLiteDatabase miBd = bdConector.getWritableDatabase();
        miBd.execSQL(cadenaSql);
        miBd.close();
        bdConector.close();
    }
    public static String [][] consultaConRetorno(Context context, String cadenaSql){
        //Select
        String [][] datos = null;
        BdOpenHelper bdConector = new BdOpenHelper(context, bdNombre, null, bdVersion);
        SQLiteDatabase miBd = bdConector.getReadableDatabase();

        //si hay algun error que se caputere con Try
        try{
            Cursor cursor = miBd.rawQuery(cadenaSql, null);
            int numFilas = cursor.getCount();
            int numColumnas = cursor.getColumnCount();
            datos = new String[numFilas][numColumnas];
            int contadorFila =0;
            while (cursor.moveToNext()){
                //si hay datos en la matriz o en el arreglo
                for (int contadorCol=0; contadorCol < numColumnas;contadorCol++){
                    datos[contadorFila][contadorCol] = cursor.getString(contadorCol);
                }
                contadorFila++;
            }
            cursor.close();
        }
        catch (Exception e){
            //Por si existe un error en la conexion de la base de datos
            Toast.makeText(context, "ERROR: "+e, Toast.LENGTH_SHORT).show();
        }
        bdConector.close();
        return datos;
    }

}

