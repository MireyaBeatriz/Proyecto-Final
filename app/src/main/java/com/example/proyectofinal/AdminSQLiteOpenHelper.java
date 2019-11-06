package com.example.proyectofinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table usuario(idusuario  INTEGER PRIMARY KEY AUTOINCREMENT, nombre text, password text)");
        sqLiteDatabase.execSQL("create table monto(idmonto  INTEGER PRIMARY KEY AUTOINCREMENT, cantidad real, fecha date)");
        sqLiteDatabase.execSQL("create table gasto(idgasto  INTEGER PRIMARY KEY AUTOINCREMENT, descripcion text, cantidad real, fecha date )");
       // sqLiteDatabase.execSQL("create table totalmonto(idtotalmonto  INTEGER PRIMARY KEY AUTOINCREMENT, detalle text,  idgasto, idmonto INTEGER, constraint ((fk_gasto)(fk_monto)) foreign key(idgasto) references gasto(idgasto), idmonto INTEGER, constraint fk_monto foreign key(idmonto) references monto(idmonto))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
