package com.joguerrero.jhonny_bd.Models;


import android.content.Context;
import android.util.Log;

import com.joguerrero.jhonny_bd.Configuracion.BdOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Articulo {

    //atributos
    //id , nombre , precio , idMarca
    private int id;
    private String nombre;
    private double precio;
    private int idMarca;
    //private Marca marca;//objeto, porque esta realacionado

    //metodos
    public Articulo (){

    }
    //construir objeto tipo articulo, desde un id
    public Articulo(int id, Context context) {
        String consultaSql ="select * from articulos where id= "+id;
        String [][] datoArticulo = BdOpenHelper.consultaConRetorno(context, consultaSql);
        if(datoArticulo != null && datoArticulo.length > 0 ){
            this.id = id;
            nombre = datoArticulo[0][1];
            precio = Double.parseDouble(datoArticulo[0][2]);
            idMarca = Integer.parseInt(datoArticulo[0][3]);
        }
    }
    //getter - setter
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
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public int getIdMarca() {
        return idMarca;
    }
    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }
    @Override
    public String toString() {
        return id+" - "+ nombre +" -- " +idMarca;
    }

    // guardar --> eliminiar modifica
    public void insertar(Context context){
        String cadenaSql="insert into articulos (nombre, precio, idMarca) values ('"+nombre+"',"+precio+","+idMarca+")";
        BdOpenHelper.consultaSinRetorno(context, cadenaSql);
        //Log.e("cadena", cadenaSql);
    }

    public void modificar(Context context, int idBd){
        String cadenaSql="update articulos set nombre ='"+nombre+"', precio ='"+precio+"', idMarca="+idMarca+" where id="+idBd;
        BdOpenHelper.consultaSinRetorno(context, cadenaSql);
        //Log.e("cadena", cadenaSql);
    }

    public void eliminar(Context context, int idBd){
        String cadenaSql="delete from articulos where id ="+idBd;
        BdOpenHelper.consultaSinRetorno(context, cadenaSql);
        //Log.e("cadena", cadenaSql);
    }

    //listar -> devuelve datos
    public static String[][] listarArticulos (Context context, String filtro, String orden){
        String cadenaSql="select * from articulos";
        if (filtro != null) cadenaSql +=" where "+filtro;
        if (orden != null) cadenaSql +=" order by "+orden;
        String[][] articulos = BdOpenHelper.consultaConRetorno(context,cadenaSql);
        return articulos;
    }
    //complemento de listararticulos
    public static List<Articulo> listarArticulosObj (Context context, String filtro, String orden){
        String[][] datos = Articulo.listarArticulos(context, filtro, orden);
        List<Articulo> listaArticulos = new ArrayList<>();
        //validar si hay datos
        if (datos != null){
            //si hay datos
            for (int contador=0; contador < datos.length; contador++){
                Articulo objArticulo = new Articulo();//crear objeto tipo Articulo
                objArticulo.id = Integer.parseInt(datos[contador][0]);
                objArticulo.nombre = datos[contador][1];
                objArticulo.precio = Double.parseDouble(datos[contador][2]);
                objArticulo.idMarca = Integer.parseInt(datos[contador][3]);
                listaArticulos.add(objArticulo);
            }
        }
        return listaArticulos;
    }
}
