package com.example.proyectofinal;

import android.content.ContentValues;
import android.content.Intent;
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

    public void Guardar (View view) {

        SQLiteDatabase bd = conexion.getWritableDatabase();
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
        Toast.makeText(this, "Registro guardado", Toast.LENGTH_SHORT).show();

    }


    public void ConsultarFecha(View v) {

        if (edtFecha.getText().toString().length() == 0) {
            edtFecha.setError("Campo obligatorio");
            estadoFecha = false;
        } else {
            estadoFecha = true;
        }

        if (estadoFecha) {
            String fecha = edtFecha.getText().toString();
            datos.setFecha(fecha);

            if (conexion.consultaFecha1(datos)) {
                edtFecha.setText(datos.getFecha());
                edtIngreso.setText("" + datos.getIngreso());
                //Toast.makeText(this, "Se encontro uno", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No existe fecha con ese dato", Toast.LENGTH_SHORT).show();

            }
        } else {
            Toast.makeText(this, "Ingrese fecha a buscar.", Toast.LENGTH_SHORT).show();
        }
    }

    public void ConsultarIngreso(View v) {
        if (edtIngreso.getText().toString().length() == 0) {
            edtIngreso.setError("Campo obligatorio");
            estadoIngreso = false;
        } else {
            estadoIngreso = true;
        }

        if (estadoIngreso) {
            String ingreso = edtIngreso.getText().toString();
            datos.setIngreso(Integer.parseInt(ingreso));

            if (conexion.consultarIngreso1(datos)) {
                edtFecha.setText(datos.getFecha());
                edtIngreso.setText("" + datos.getIngreso());
                //Toast.makeText(this, "Se encontro uno", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No existe ingreso con ese dato", Toast.LENGTH_SHORT).show();

            }
        } else {
            Toast.makeText(this, "Ingrese monto a buscar.", Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminarporcodigo(View v) {

        MontoDto datos = new MontoDto();
        if (edtIngreso.getText().toString().length() == 0) {
            edtIngreso.setError("campo obligatorio");
            estadoIngreso = false;

        } else {
            estadoIngreso = true;
        }

        if (estadoIngreso) {
            String id = edtIngreso.getText().toString();
            datos.setIdmonto(Integer.parseInt(id));
            if (conexion.EliminarMonto(MontoInicial.this, datos)) { //Toast.makeText(this, "Registro eliminado satisfactoriamente.", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MontoInicial.this, ListaMonto.class);
                startActivity(i);
                //limpiarDatos();
            } else {
                Toast.makeText(this, "No existe un artículo con dicho código.", Toast.LENGTH_SHORT).show();
                //limpiarDatos();
            }
        }

    /*public void DespuesEliminar() {
        Intent i = new Intent(MontoInicial.this,ListaMonto.class);
        startActivity(i);
    }*/
    }

    public void modificacion(View v) {

        if(edtFecha.getText().toString().length()==0){
            edtFecha.setError("campo obligatorio");
            estadoFecha = false;
        }else { estadoFecha=true;
        }

        if(estadoFecha) {
            SQLiteDatabase bd = conexion.getWritableDatabase();
            String idmonto =  null;
            String fecha = edtFecha.getText().toString();
            String ingreso = edtIngreso.getText().toString();

            ContentValues registro = new ContentValues();
            registro.put("idmonto", idmonto);
            registro.put("fecha", fecha);
            registro.put("ingreso", ingreso);

            bd.update("monto", registro, "idmonto="+idmonto, null);
            bd.close();

            edtFecha.requestFocus();
            Toast.makeText(this, "Monto modificado", Toast.LENGTH_SHORT).show();
            Nuevo(v);
        }
    }



}
