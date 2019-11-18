package com.example.proyectofinal;

import android.content.ContentValues;

import android.content.Intent;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class DetalleMonto extends AppCompatActivity {

    private TextView tv_id, tv_fecha, tv_ingreso;
    private TextView tv_id1, tv_fecha1, tv_ingreso1;

    private boolean estadoid = false;
    MontoDto montoDto = null;
    Conexion conexion = new Conexion(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_monto);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tv_id = (TextView) findViewById(R.id.tv_id);
        tv_fecha = (TextView) findViewById(R.id.tv_fecha);
        tv_ingreso = (TextView) findViewById(R.id.tv_ingreso);
        tv_id1 = (TextView) findViewById(R.id.tv_id1);
        tv_fecha1 = (TextView) findViewById(R.id.tv_fecha1);
        tv_ingreso1 = (TextView) findViewById(R.id.tv_ingreso1);

        Bundle objeto = getIntent().getExtras();


        if (objeto != null) {

        }
        montoDto = (MontoDto) objeto.getSerializable("monto");
        tv_id.setText("" + montoDto.getIdmonto());
        tv_fecha.setText(montoDto.getFecha());
        tv_ingreso.setText(String.valueOf(montoDto.getIngreso()));
        tv_id1.setText("" + montoDto.getIdmonto());


        tv_fecha1.setText(montoDto.getFecha());
        tv_ingreso1.setText(String.valueOf(montoDto.getIngreso()));


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void eliminarporcodigo(View v) {
        MontoDto datos = new MontoDto();
        if (tv_id.getText().toString().length() == 0) {
            tv_id.setError("campo obligatorio");
            estadoid = false;
        } else { estadoid = true; }

        if (estadoid) {
            String id = tv_id.getText().toString();
            datos.setIdmonto(Integer.parseInt(id));
            if (conexion.EliminarMonto(DetalleMonto.this, datos)) { //Toast.makeText(this, "Registro eliminado satisfactoriamente.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No existe un artículo con dicho código.", Toast.LENGTH_SHORT).show();
                //limpiarDatos();
            }
        }
    }
    public void Editar(View v) {


        Intent i = new Intent(DetalleMonto.this,EditarMonto.class);


        i.putExtra("senal","1");
        i.putExtra("idmonto",String.valueOf(montoDto.getIdmonto()));
        i.putExtra("fecha",montoDto.getFecha());
        i.putExtra("ingreso",String.valueOf(montoDto.getIngreso()));

        startActivity(i);

             Toast.makeText(DetalleMonto.this, "idmonto:" +montoDto.getIdmonto()+"\n" +
                      "fecha" +montoDto.getFecha()+"ingreso"+montoDto.getIngreso(), Toast.LENGTH_SHORT).show();




    }
}


