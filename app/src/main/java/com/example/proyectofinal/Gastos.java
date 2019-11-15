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
    private Button btn_guardar, btn_nuevo, btn_eliminar, btn_modificar, btn_buscar,btn_consultar;

    boolean estadodescripcion = false;
    boolean estadofecha = false;
    boolean estadomonto = false;
    boolean estadoid = false;


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
        et_descripcion.setText("");
        et_fecha.setText("");
        et_monto.setText("");
        et_descripcion.requestFocus();

        Toast.makeText(this, "Ingrese todos los datos", Toast.LENGTH_SHORT).show();
        //Toast.makeText(getContext(), "Ingrese todos los datos", Toast.LENGTH_SHORT).show();
    }
    public void Guardar (View view) {

        SQLiteDatabase bd = conexion.getWritableDatabase();
        String idgasto = null;
        String descripcion = et_descripcion.getText().toString();
        String fecha = et_fecha.getText().toString();
        String monto = et_monto.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("idgasto", idgasto);
        registro.put("descripcion", descripcion);
        registro.put("fecha", fecha);
        registro.put("monto", monto);


        bd.insert("gasto", null, registro);
        bd.close();

        et_descripcion.setText("");
        et_fecha.setText("");
        et_monto.setText("");
        et_descripcion.requestFocus();
        Toast.makeText(this, "Registro guardado", Toast.LENGTH_SHORT).show();

    }
    public void ConsultarFecha(View v) {

        if (et_fecha.getText().toString().length() == 0) {
            et_fecha.setError("Campo obligatorio");
            estadofecha = false;
        } else {
            estadofecha = true;
        }

        if (estadofecha) {
            String fecha = et_fecha.getText().toString();
            datos.setEt_fecha(fecha);

            if (conexion.consultaFechaGasto(datos)) {
                et_descripcion.setText("" + datos.getEt_descripcion());
                et_fecha.setText(datos.getEt_fecha());
                et_monto.setText("" + datos.getEt_monto());
                //Toast.makeText(this, "Se encontro uno", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No existe fecha con ese dato", Toast.LENGTH_SHORT).show();

            }
        } else {
            Toast.makeText(this, "Ingrese fecha a buscar.", Toast.LENGTH_SHORT).show();
        }
    }
    public void ConsultarDescripcion(View v) {

        if (et_descripcion.getText().toString().length() == 0) {
            et_descripcion.setError("Campo obligatorio");
            estadodescripcion = false;
        } else {
            estadodescripcion = true;
        }

        if (estadodescripcion) {
            String descripcion = et_descripcion.getText().toString();
            datos.setEt_descripcion(descripcion);

            if (conexion.consultaDescripcion(datos)) {
                et_descripcion.setText("" + datos.getEt_descripcion());
                et_fecha.setText(datos.getEt_fecha());
                et_monto.setText("" + datos.getEt_monto());
                //Toast.makeText(this, "Se encontro uno", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No existe fecha con ese dato", Toast.LENGTH_SHORT).show();

            }
        } else {
            Toast.makeText(this, "Ingrese fecha a buscar.", Toast.LENGTH_SHORT).show();
        }
    }


}

