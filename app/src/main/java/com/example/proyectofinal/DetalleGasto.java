package com.example.proyectofinal;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class DetalleGasto  extends AppCompatActivity {

    private TextView tv_id, tv_descripcion, tv_fecha, tv_monto;
    private TextView tv_id1, tv_descripcion1, tv_fecha1, tv_monto1;

    private boolean estadoid= false;


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

    public void eliminarporid(View v) {
        GastosDto datos = new GastosDto();
        if (tv_id.getText().toString().length() == 0) {
            tv_id.setError("campo obligatorio");
            estadoid = false;

        } else {
            estadoid = true;
        }

        if (estadoid) {
            String id = tv_id.getText().toString();
            datos.setIdgasto(Integer.parseInt(id));
            if (conexion.ElimiarGasto(DetalleGasto.this, datos)) { //Toast.makeText(this, "Registro eliminado satisfactoriamente.", Toast.LENGTH_SHORT).show();
                //limpiarDatos();
<<<<<<< HEAD
            } else {
                Toast.makeText(this, "No existe un artículo con dicho código.", Toast.LENGTH_SHORT).show();
=======
            }else{
                Toast.makeText(this, "No existe un gasto con dicho id.", Toast.LENGTH_SHORT).show();
>>>>>>> a487e2ef6ec8f0e9717d629c01617bea05a273b6
                //limpiarDatos();
            }
        }
    }
        public void modificarporid(View v) {
        GastosDto datos = new GastosDto();
            if(tv_id.getText().toString().length()==0){
                tv_id.setError("campo obligatorio");
                estadoid = false;

            }else { estadoid=true;
            }

            if(estadoid) {
                String id = tv_id.getText().toString();
                datos.setIdgasto(Integer.parseInt(id));


                if(conexion.modificarporid(datos)){
                    Toast.makeText(this, "Registro Modificado Correctamente.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "No se han encontrado resultados para la busqueda especificada.", Toast.LENGTH_SHORT).show();
                }
            }
        }
}

