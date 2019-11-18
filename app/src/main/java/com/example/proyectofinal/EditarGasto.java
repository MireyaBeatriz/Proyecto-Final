package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditarGasto extends AppCompatActivity {

    GastosDto datos = new GastosDto();
Conexion conexion = new Conexion(this);
    boolean estadoidgasto = false;


    private EditText et_descripcion, et_fecha, et_monto,edtIdgasto;
private Button btnEditar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_gasto);

        edtIdgasto = findViewById(R.id.edtIdgasto);
        btnEditar = findViewById(R.id.btnEditar);
        et_descripcion = findViewById(R.id.et_descripcion);
        et_fecha = findViewById(R.id.et_fecha);
        et_monto = findViewById(R.id.et_monto);

//desde aqui empieza lo de modificar
        String senal = "";
        String idgasto ="";
        String descripcion ="";
        String fecha = "";
        String monto = "";
        try{
           Intent intent = getIntent();
           Bundle bundle = intent.getExtras();
           if (bundle !=null){
               idgasto = bundle.getString("idgasto");
               senal = bundle.getString("senal");
               descripcion = bundle.getString("descripcion");
               fecha = bundle.getString("fecha");
               monto = bundle.getString("monto");


               if (senal.equals("1")){
                    edtIdgasto.setText(idgasto);
                   et_descripcion.setText(descripcion);
                   et_fecha.setText(fecha);
                   et_monto.setText(monto);
               }
           }
        }catch (Exception e){

        }
    }

    public void modificar(View v) {
        if(edtIdgasto.getText().toString().length()==0){
            edtIdgasto.setError("campo obligatorio");
            estadoidgasto = false;

        }else { estadoidgasto=true;
        }

        if(estadoidgasto) {
            String idgasto = edtIdgasto.getText().toString();
            String descripcion = et_descripcion.getText().toString();
            String fecha =(et_fecha.getText().toString());
            int monto = Integer.parseInt(et_monto.getText().toString());

            datos.setIdgasto(Integer.parseInt(idgasto));
            datos.setEt_descripcion(descripcion);
            datos.setEt_fecha(fecha);
            datos.setEt_monto(monto);

            if(conexion.modificar(datos)){
                Toast.makeText(this, "Registro Modificado Correctamente.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "No se han encontrado resultados para la busqueda especificada.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
