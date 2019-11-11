package com.example.proyectofinal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Conexion extends SQLiteOpenHelper {

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

    public boolean InsertDetalle(DetalleDto datos) {
        boolean estado = true;
        try {
            int id_totalmonto = datos.getId_totalmonto();
            String detalle = datos.getDetalle();


            Cursor fila = bd().rawQuery("select id_totalmonto from totalmonto where id_totalmonto='" + id_totalmonto + "'", null);
            if (fila.moveToFirst() == true) {
                estado = false;
            } else {
                String SQL = "INSERT INTO totalmonto \n" +
                        "(id_totalmonto, detalle)\n" +
                        "VALUES \n" +
                        "('" + String.valueOf(id_totalmonto) + "', '" + detalle +  "');";
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

}
