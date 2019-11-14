package com.example.proyectofinal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class Conexion extends SQLiteOpenHelper {
    ArrayList<String> listaMonto;
    ArrayList<MontoDto> montoList;

    ArrayList<String> listaGasto;
    ArrayList<GastosDto> gastoList;
    MontoDto datos = new MontoDto();

    public Conexion(Context context) {

        super(context, "administracion.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table usuario(idusuario  INTEGER PRIMARY KEY AUTOINCREMENT, nombre text, password text)");
        sqLiteDatabase.execSQL("create table monto(idmonto  INTEGER PRIMARY KEY AUTOINCREMENT, ingreso real, fecha date)");
        sqLiteDatabase.execSQL("create table gasto(idgasto  INTEGER PRIMARY KEY AUTOINCREMENT, descripcion text, monto real, fecha date )");
       // sqLiteDatabase.execSQL("create table totalmonto(idtotalmonto  INTEGER PRIMARY KEY AUTOINCREMENT, detalle text,  idgasto, idmonto INTEGER, constraint ((fk_gasto)(fk_monto)) foreign key(idgasto) references gasto(idgasto), idmonto INTEGER, constraint fk_monto foreign key(idmonto) references monto(idmonto))");
        sqLiteDatabase.execSQL("create table totalmonto(id_totalmonto  INTEGER PRIMARY KEY AUTOINCREMENT, detalle text, idmonto INTEGER, idgasto INTEGER, constraint fk_monto foreign key(idmonto) references monto(idmonto), constraint fk_gasto foreign key(idgasto) references gasto(idgasto))");


    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public SQLiteDatabase bd() {
        SQLiteDatabase bd = this.getWritableDatabase();
        return bd;
    }
    public boolean InsertMonto(MontoDto datos) {
        boolean estado = true;
        try {
            int idmonto = datos.getIdmonto();
            String fecha = datos.getFecha();
            double cantidad = datos.getIngreso();

            Cursor fila = bd().rawQuery("select idmonto from monto where idmonto='" + idmonto + "'", null);
            if (fila.moveToFirst() == true) {
                estado = false;
            } else {
                String SQL = "INSERT INTO monto \n" +
                        "(idmonto, fecha, cantidad)\n" +
                        "VALUES \n" +
                        "('" + String.valueOf(idmonto) + "', '" + fecha + "', '" + String.valueOf(cantidad) + "');";
                bd().execSQL(SQL);
                bd().close();
                estado = true;
            }
        } catch (Exception e) {
            estado = true;
            Log.e("error.", e.toString());
        }
        return estado;

    }

    public boolean consultaFechaGasto(GastosDto datos) {
        boolean estado = true;
        int resultado;
        //SQLiteDatabase bd = this.getWritableDatabase();
        SQLiteDatabase bd = this.getReadableDatabase();
        try {
            String[] parametros = {String.valueOf(datos.getEt_fecha())};
            String[] campos = {"descripcion", "fecha", "monto"};
            Cursor fila = bd.query("gasto", campos, "fecha=?", parametros, null, null, null);
            // fila.moveToFirst();
            if (fila.moveToFirst()) {
                datos.setEt_descripcion(fila.getString(0));
                datos.setEt_fecha(fila.getString(1));
                datos.setEt_monto(Double.parseDouble(fila.getString(2)));
                estado = true;
            } else {
                estado = false;
            }
            fila.close();
            bd.close();
        } catch (Exception e) {
            estado = false;
            Log.e("error.", e.toString());
        }
        return estado;
    }


    public boolean consultaFecha1(MontoDto datos) {
        boolean estado = true;
        int resultado;
        //SQLiteDatabase bd = this.getWritableDatabase();
        SQLiteDatabase bd = this.getReadableDatabase();
        try {
            String[] parametros = {String.valueOf(datos.getFecha())};
            String[] campos = {"fecha", "ingreso"};
            Cursor fila = bd.query("monto", campos, "fecha=?", parametros, null, null, null);
            // fila.moveToFirst();
            if (fila.moveToFirst()) {
                datos.setFecha(fila.getString(0));
                datos.setIngreso(Double.parseDouble(fila.getString(1)));
                estado = true;
            } else {
                estado = false;
            }
            fila.close();
            bd.close();
        } catch (Exception e) {
            estado = false;
            Log.e("error.", e.toString());
        }
        return estado;
    }


    public boolean consultarIngreso1(MontoDto datos) {
        boolean estado = true;
        int resultado;
        //SQLiteDatabase bd = this.getWritableDatabase();
        SQLiteDatabase bd = this.getReadableDatabase();
        try {
            String[] parametros = {String.valueOf(datos.getIngreso())};
            String[] campos = {"fecha", "ingreso"};
            Cursor fila = bd.query("monto", campos, "ingreso=?", parametros, null, null, null);
            // fila.moveToFirst();
            if (fila.moveToFirst()) {
                datos.setFecha(fila.getString(0));
                datos.setIngreso(Double.parseDouble(fila.getString(1)));
                estado = true;
            } else {
                estado = false;
            }
            fila.close();
            bd.close();
        } catch (Exception e) {
            estado = false;
            Log.e("error.", e.toString());
        }
        return estado;
    }

    public ArrayList<MontoDto> consultaListaMonto() {
        boolean estado = false;
        //SQLiteDatabase bd = this.getWritableDatabase();
        SQLiteDatabase bd = this.getReadableDatabase();
        MontoDto monto = null;
        //Creamos la instancia vacia.
        montoList = new ArrayList<MontoDto>();

        try {
            Cursor fila = bd.rawQuery("select * from monto", null);

            while (fila.moveToNext()) {

                monto = new MontoDto();
                monto.setIdmonto(fila.getInt(0));
                monto.setFecha(fila.getString(1));
                monto.setIngreso(fila.getDouble(2));

                montoList.add(monto);

                Log.i("idmonto", String.valueOf(monto.getIdmonto()));
                Log.i("fecha", monto.getFecha().toString());
                Log.i("ingreso", String.valueOf(monto.getIngreso()));
            }
            obtenerListaMonto();

        } catch (Exception e) {

        }
        return montoList;
    }

    public ArrayList<String> obtenerListaMonto() {
        listaMonto = new ArrayList<String>();
        listaMonto.add("seleccione");

        for (int i = 0; i < montoList.size(); i++) {
            listaMonto.add(montoList.get(i).getIdmonto() + " ~ " + montoList.get(i).getFecha());

        }
        return listaMonto;
    }
    //Fin del Spinner.

    // Inicio del Método para crear lista de datos de la BD en el ListView.

    public ArrayList<String> consultaListaMonto1(){
        boolean estado = false;
        //SQLiteDatabase bd = this.getWritableDatabase();
        SQLiteDatabase bd = this.getReadableDatabase();

        MontoDto monto = null;
        //Creamos la instancia vacia.
        montoList = new ArrayList<MontoDto>();

        try{
            Cursor fila = bd.rawQuery("select * from monto",null);
            while (fila.moveToNext()){
                monto = new MontoDto();
                monto.setIdmonto(fila.getInt(0));
                monto.setFecha(fila.getString(1));
                monto.setIngreso(fila.getDouble(2));

                montoList.add(monto);
            }
            listaMonto = new ArrayList<String>();
            //listaArticulos = new ArrayList<>();
            // listaArticulos.add("Seleccione");

            for(int i=0;i<=montoList.size();i++){
                // listaArticulos.add(String.valueOf(articulosList.get(i).getCodigo()));
                listaMonto.add(montoList.get(i).getIdmonto()+" ~ "+montoList.get(i).getFecha());
            }
            //bd().close();
            // return listaArticulos;
        }catch (Exception e){

        }
        //return articulosList;
        return listaMonto;
    }


    // public void consultaListaArticulos(){
    public ArrayList<GastosDto> consultaListaGasto() {
        boolean estado = false;
        //SQLiteDatabase bd = this.getWritableDatabase();
        SQLiteDatabase bd = this.getReadableDatabase();
        GastosDto gasto = null;
        //Creamos la instancia vacia.
        gastoList = new ArrayList<GastosDto>();

        try {
            Cursor fila = bd.rawQuery("select * from gasto", null);

            while (fila.moveToNext()) {

                gasto = new GastosDto();
                gasto.setIdgasto(fila.getInt(0));
                gasto.setEt_fecha(fila.getString(1));
                gasto.setEt_descripcion(fila.getString(2));
                gasto.setEt_monto(fila.getDouble(3));

                gastoList.add(gasto);

                Log.i("id", String.valueOf(gasto.getIdgasto()));
                Log.i("fecha", gasto.getEt_fecha().toString());
                Log.i("descripción", gasto.getEt_descripcion().toString());
                Log.i("monto", String.valueOf(gasto.getEt_monto()));
            }
            obtenerListagasto();

        } catch (Exception e) {

        }
        return gastoList;
    }

    public ArrayList<String> obtenerListagasto() {
        listaGasto = new ArrayList<String>();
        listaGasto.add("seleccione");

        for (int i = 0; i < gastoList.size(); i++) {
            listaGasto.add(gastoList.get(i).getIdgasto() + " ~ " + gastoList.get(i).getEt_descripcion());

        }
        return listaGasto;
    }
    //Fin del Spinner.

    // Inicio del Método para crear lista de datos de la BD en el ListView.

    public ArrayList<String> consultaListagasto1(){
        boolean estado = false;
        //SQLiteDatabase bd = this.getWritableDatabase();
        SQLiteDatabase bd = this.getReadableDatabase();

        GastosDto gasto = null;
        //Creamos la instancia vacia.
        gastoList = new ArrayList<GastosDto>();

        try{
            Cursor fila = bd.rawQuery("select * from gasto",null);
            while (fila.moveToNext()){
                gasto = new GastosDto();
                gasto.setIdgasto(fila.getInt(0));
                gasto.setEt_descripcion(fila.getString(1));
                gasto.setEt_fecha(fila.getString(2));
                gasto.setEt_monto(fila.getDouble(3));

                gastoList.add(gasto);
            }
            listaGasto = new ArrayList<String>();
            //listaArticulos = new ArrayList<>();
            // listaArticulos.add("Seleccione");

            for(int i=0;i<=gastoList.size();i++){
                // listaArticulos.add(String.valueOf(articulosList.get(i).getCodigo()));
                listaGasto.add(gastoList.get(i).getIdgasto()+" ~ "+gastoList.get(i).getEt_descripcion());
            }
            //bd().close();
            // return listaArticulos;
        }catch (Exception e){

        }
        //return articulosList;
        return listaGasto;
    }
}
