package com.example.proyectofinal;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class DetalleGasto  extends AppCompatActivity {

    private TextView tv_id, tv_descripcion, tv_fecha, tv_monto;
    private TextView tv_id1, tv_descripcion1, tv_fecha1, tv_monto1;



    Conexion conexion = new Conexion(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_gasto);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tv_id = (TextView)findViewById(R.id.tv_id);
        tv_descripcion= (TextView)findViewById(R.id.tv_descripcion);
        tv_fecha= (TextView)findViewById(R.id.tv_fecha);
        tv_monto= findViewById(R.id.tv_monto);
        tv_id1 = (TextView)findViewById(R.id.tv_id1);
        tv_descripcion1= (TextView)findViewById(R.id.tv_descripcion1);
        tv_fecha1 = (TextView)findViewById(R.id.tv_fecha1);
        tv_monto1 = (TextView)findViewById(R.id.tv_monto1);

        Bundle objeto = getIntent().getExtras();
        GastosDto gastosDto = null;

        if(objeto != null){

        }
        gastosDto = (GastosDto)objeto.getSerializable("gasto");
        tv_id.setText(""+gastosDto.getIdgasto());
        tv_descripcion.setText(""+gastosDto.getEt_descripcion());
        tv_fecha.setText(gastosDto.getEt_fecha());
        tv_monto.setText(String.valueOf(gastosDto.getEt_monto()));
        tv_id1.setText(""+gastosDto.getIdgasto());
        tv_descripcion1.setText(""+gastosDto.getEt_descripcion());
        tv_fecha1.setText(gastosDto.getEt_fecha());
        tv_monto1.setText(String.valueOf(gastosDto.getEt_monto()));


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
