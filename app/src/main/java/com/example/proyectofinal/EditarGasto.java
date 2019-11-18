package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditarGasto extends AppCompatActivity {

    private EditText et_descripcion, et_fecha, et_monto,edtIdmonto;
private Button btnEditar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_gasto);

        edtIdmonto = findViewById(R.id.edtIdmonto);
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
                    edtIdmonto.setText(idgasto);
                   et_descripcion.setText(descripcion);
                   et_fecha.setText(fecha);
                   et_monto.setText(monto);
               }
           }
        }catch (Exception e){

        }
    }
}
