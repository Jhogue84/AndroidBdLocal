package com.joguerrero.jhonny_bd.Models;

import android.content.Context;
import android.util.Log;

import com.joguerrero.jhonny_bd.Configuracion.BdOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Marca {
    //atributos, nombre de columnas tabla marcas
    private int id;
    private String nombre;
    private String descripcion;

    //metodos

    //construir un objeto tipo Marca, apartir del id
    public Marca (){

    }
    //construir objeto tipo marca, desde un id
    public Marca(int id, Context context) {
        String consultaSql ="select * from marcas where id= "+id;
        //BdOpenHelper prueba = new BdOpenHelper(context, bddato, null, veriosn);
        String [][] datoMarca = BdOpenHelper.consultaConRetorno(context, consultaSql);
        if(datoMarca != null && datoMarca.length > 0 ){
            this.id = id;
            nombre = datoMarca[0][1];
            descripcion = datoMarca[0][2];
        }
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    @Override
    public String toString() {
        return id +"-"+ nombre;
    }
    //CRUD
    // guardar --> eliminiar modifica
    public void insertar(Context context){
        String cadenaSql="insert into marcas (nombre, descripcion) values ('"+nombre+"','"+descripcion+"')";
        BdOpenHelper.consultaSinRetorno(context, cadenaSql);
    }

    public void modificar(Context context, int idBd){
        String cadenaSql="update marcas set nombre ='"+nombre+"', descripcion ='"+descripcion+"' where id="+idBd;
        BdOpenHelper.consultaSinRetorno(context, cadenaSql);
    }

    public void eliminar(Context context, int idBd){
        String cadenaSql="delete from marcas where id ="+idBd;
        BdOpenHelper.consultaSinRetorno(context, cadenaSql);
    }

    //listar -> devuelve datos
    public static String[][] listarMarcas (Context context, String filtro, String orden){
        String cadenaSql="select * from marcas";
        if (filtro != null) cadenaSql +=" where "+filtro;
        if (orden != null) cadenaSql +=" order by "+orden;
        String[][] marcas = BdOpenHelper.consultaConRetorno(context,cadenaSql);
        return marcas;
    }
    //complemento de listarmarcas
    public static List<Marca> listarMarcasObj (Context context, String filtro, String orden){
        String[][] datos = Marca.listarMarcas(context, filtro, orden);
        List<Marca> listaMarcas = new ArrayList<>();
        //validar si hay datos
        if (datos != null){
            //si hay datos
            for (int contador=0; contador < datos.length; contador++){
                Marca objMarca = new Marca();
                objMarca.id = Integer.parseInt(datos[contador][0]);
                objMarca.nombre = datos[contador][1];
                objMarca.descripcion = datos[contador][2];
                listaMarcas.add(objMarca);
            }
        }
        return listaMarcas;
    }
}

