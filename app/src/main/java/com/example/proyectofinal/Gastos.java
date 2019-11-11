package com.example.proyectofinal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Gastos extends AppCompatActivity {

    private EditText et_descripcion, et_fecha, et_monto;
    private Button btn_guardar, btn_nuevo, btn_eliminar, btn_modificar, btn_buscar;

    boolean estadodescripcion = false;
    boolean estadofecha = false;
    boolean estadomonto = false;


    Conexion conexion = new Conexion(this);
    GastosDto datos = new GastosDto();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gastos);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        et_descripcion = (EditText) findViewById(R.id.et_descripcion);
        et_fecha = (EditText) findViewById(R.id.et_fecha);
        et_monto = (EditText) findViewById(R.id.et_monto);

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
        et_descripcion.setText(null);
        et_fecha.setText(null);
        et_monto.setText(null);
    }

    public void Guardar(View view) {
        if (et_descripcion.getText().toString().length() == 0) {
            estadodescripcion = false;
            et_descripcion.setError("Campo obligatorio");
        } else {
            estadodescripcion = true;
        }

        if (et_fecha.getText().toString().length() == 0) {
            estadofecha = false;
            et_fecha.setError("Campo obligatorio");
        } else {
            estadofecha = true;
        }
        if (et_monto.getText().toString().length() == 0) {
            estadomonto = false;
            et_monto.setError("Campo obligatorio");
        } else {
            estadomonto = true;
        }

        if (estadodescripcion && estadofecha && estadomonto) {
            try {
                // datos.setIdmonto(Integer.parseInt(edtFecha.Null);
                datos.setEt_descripcion(et_descripcion.getText().toString());
                datos.setEt_fecha(et_fecha.getText().toString());
                datos.setEt_monto(Double.parseDouble(et_monto.getText().toString()));
                //if(conexion.insertardatos(datos)){ //if(conexion.InsertRegister(datos)){
                if (conexion.InsertarGastos(datos)) {
                    Toast.makeText(this, "Registro agregado satisfactoriamente!", Toast.LENGTH_SHORT).show();
                    Nuevo(view);
                } else {
                    Toast.makeText(getApplicationContext(), "Error. Ya existe un registro\n" + " Fecha: " + et_fecha.getText().toString(), Toast.LENGTH_LONG).show();
                    Nuevo(view);
                }
            } catch (Exception e) {
                Toast.makeText(this, "ERROR. Ya existe.", Toast.LENGTH_SHORT).show();
            }
        }


            }
        }

