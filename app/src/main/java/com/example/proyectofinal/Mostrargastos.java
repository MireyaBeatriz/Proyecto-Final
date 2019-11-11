package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Mostrargastos extends AppCompatActivity {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //objetos java
    private ListView livEmpleados;

    //arrays
    ArrayList<String> listaEmpleados;
    ArrayList<Gastos> empleadosList;

    //para la conexión
    Conexion admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrargastos);
    }
}
