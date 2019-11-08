package com.example.proyectofinal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MontoInicial extends AppCompatActivity {

    private EditText edtFecha, edtIngreso;
    boolean estadoFecha = false;
    boolean estadoIngreso = false;

    Conexion conexion = new Conexion(this);
    MontoDto datos = new MontoDto();

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
        if (edtFecha.getText().toString().length() == 0) {
            estadoFecha = false;
            edtFecha.setError("Campo obligatorio");
        } else {
            estadoFecha = true;
        }

        if (edtIngreso.getText().toString().length() == 0) {
            estadoIngreso = false;
            edtIngreso.setError("Campo obligatorio");
        } else {
            estadoIngreso = true;
        }

        if (estadoFecha && estadoIngreso) {
            try {
                // datos.setIdmonto(Integer.parseInt(edtFecha.Null);
                datos.setFecha(edtFecha.getText().toString());
                datos.setIngreso(Double.parseDouble(edtIngreso.getText().toString()));
                //if(conexion.insertardatos(datos)){ //if(conexion.InsertRegister(datos)){
                if (conexion.InsertMonto(datos)) {
                    Toast.makeText(this, "Registro agregado satisfactoriamente!", Toast.LENGTH_SHORT).show();
                    Nuevo(view);
                } else {
                    Toast.makeText(getApplicationContext(), "Error. Ya existe un registro\n" + " Fecha: " + edtFecha.getText().toString(), Toast.LENGTH_LONG).show();
                    Nuevo(view);
                }
            } catch (Exception e) {
                Toast.makeText(this, "ERROR. Ya existe.", Toast.LENGTH_SHORT).show();
            }
        }


    }
}
