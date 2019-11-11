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

    public Conexion(Context context) {

        super(context, "administracion.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table usuario(idusuario  INTEGER PRIMARY KEY AUTOINCREMENT, nombre text, password text)");
        sqLiteDatabase.execSQL("create table monto(idmonto  INTEGER PRIMARY KEY AUTOINCREMENT, cantidad real, fecha date)");
        sqLiteDatabase.execSQL("create table gasto(idgasto  INTEGER PRIMARY KEY AUTOINCREMENT, descripcion text, cantidad real, fecha date )");
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



    public boolean InsertarGastos(GastosDto datos) {
        boolean estado = true;
        try {
            int idgasto = datos.getIdgasto();
            String descripcion = datos.getEt_descripcion();
            String fecha = datos.getEt_fecha();
            double cantidad = datos.getEt_monto();

            Cursor fila = bd().rawQuery("select idgasto from gasto where idgasto='" + idgasto + "'", null);
            if (fila.moveToFirst() == true) {
                estado = false;
            } else {
                String SQL = "INSERT INTO gasto \n" +
                        "(idgasto, descripcion, fecha, cantidad)\n" +
                        "VALUES \n" +
                        "('" + String.valueOf(idgasto) + "', '" + descripcion + "','" + fecha + "', '" + String.valueOf(cantidad) + "');";
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


    public boolean consultaFecha1(MontoDto datos) {
        boolean estado = true;
        int resultado;
        SQLiteDatabase bd = this.getWritableDatabase();
        try {
            String fecha = datos.getFecha();
            double ingreso = datos.getIngreso();
            //Cursor fila = bd.rawQuery("select descripcion, precio from articulos where codigo='" + codigo + "'", null);
            // Cursor fila = bd.rawQuery("select descripcion, precio from articulos where codigo=" + codigo, null);
            Cursor fila = bd.rawQuery("select fecha, ingreso from monto where fecha=" + fecha, null);
            if (fila.moveToFirst()) {
                datos.setFecha(fila.getString(0));
                datos.setIngreso(Double.parseDouble(fila.getString(1)));
                estado = true;
            } else {
                estado = false;
            }
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
        SQLiteDatabase bd = this.getWritableDatabase();
        try {
            double ingreso = datos.getIngreso();
            String fecha = datos.getFecha();
            Cursor fila = bd.rawQuery("select fecha, ingreso from monto where ingreso='" + ingreso + "'", null);
            if (fila.moveToFirst()) {
                datos.setIngreso(Double.parseDouble(fila.getString(0)));
                datos.setFecha(fila.getString(1));

                estado = true;
            } else {
                estado = false;
            }
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
            Cursor fila = bd.rawQuery("select * from articulos", null);

            while (fila.moveToNext()) {

                monto = new MontoDto();
                monto.setIdmonto(fila.getInt(0));
                monto.setFecha(fila.getString(1));
                monto.setIngreso(fila.getDouble(2));

                montoList.add(monto);

                Log.i("Id", String.valueOf(monto.getIdmonto()));
                Log.i("Fecha", monto.getFecha().toString());
                Log.i("Ingreso", String.valueOf(monto.getIngreso()));
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
}
