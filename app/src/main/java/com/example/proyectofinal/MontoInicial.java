package com.example.proyectofinal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MontoInicial extends AppCompatActivity {

    private EditText edtFecha, edtIngreso;

    AdminSQLiteOpenHelper admin;

    public MontoInicial() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monto_inicial);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtFecha = findViewById(R.id.edtFecha);
        edtIngreso = findViewById(R.id.edtIngreso);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void Nuevo(View view) {
        edtFecha.setText("");
        edtIngreso.setText("");
        edtFecha.requestFocus();

        Toast.makeText(this, "Ingrese todos los datos", Toast.LENGTH_SHORT).show();
        //Toast.makeText(getContext(), "Ingrese todos los datos", Toast.LENGTH_SHORT).show();
    }

    public void Guardar(View view) {
        if (edtFecha.equals("") || edtIngreso.equals("")) {

            Toast.makeText(this, "Fecha y Monto de ingreso \n ES OBLIGATORIO", Toast.LENGTH_SHORT).show();
            // Toast.makeText(getContext(), "Nombre, salario y seleccionar un tipo de empleado.\n ES OBLIGATORIO", Toast.LENGTH_LONG).show();

        } else {
            SQLiteDatabase bd = admin.getWritableDatabase();
            String idmonto = null;
            String fecha = edtFecha.getText().toString();
            String ingreso = edtIngreso.getText().toString();


            ContentValues registro = new ContentValues();
            registro.put("idmonto", idmonto);
            registro.put("fecha", fecha);
            registro.put("ingreso", ingreso);


            bd.insert("monto", null, registro);
            bd.close();

            edtFecha.setText("");
            edtIngreso.setText("");
            edtFecha.requestFocus();
            Toast.makeText(this, "Monto guardado correctamente", Toast.LENGTH_SHORT).show();
            //Toast.makeText(getContext(), "Empleado guardado de tipo: " + idtipo, Toast.LENGTH_SHORT).show();
        }

    }
}
