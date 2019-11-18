package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditarMonto extends AppCompatActivity {

    boolean estadoIdmonto = false;


    private Button btnEditar;
    private EditText edtIdmonto, edtFecha, edtIngreso;

    MontoInicial datos = new MontoInicial();
    MontoDto datos1 = new MontoDto();
    Conexion conexion = new Conexion(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_monto);

        btnEditar = findViewById(R.id.btnEditar);
        edtIdmonto = findViewById(R.id.edtIdmonto);
        edtFecha = findViewById(R.id.edtFecha);
        edtIngreso = findViewById(R.id.edtIngreso);

//desde aqui empieza lo de modificar
        String senal = "";
        String idmonto ="";
        String fecha = "";
        String ingreso = "";

        try{
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();

            if (bundle !=null){
                idmonto = bundle.getString("idmonto");
                senal = bundle.getString("senal");
               fecha = bundle.getString("fecha");
                ingreso = bundle.getString("ingreso");


               /* Toast.makeText(EditarMonto.this, "idmonto:" +idmonto+"\n" +
                        "fecha" +fecha +"ingreso"+ ingreso, Toast.LENGTH_SHORT).show();*/

                if (senal.equals("1")){
                   edtIdmonto.setText(idmonto);
                    edtFecha.setText(fecha);
                    edtIngreso.setText(ingreso);

                }
            }
        }catch (Exception e){

        }
    }
        public void modificar(View v) {
            if(edtIdmonto.getText().toString().length()==0){
                edtIdmonto.setError("campo obligatorio");
                estadoIdmonto = false;

            }else { estadoIdmonto=true;
            }

            if(estadoIdmonto) {
                String id = edtIdmonto.getText().toString();
                String fecha = edtFecha.getText().toString();
                int ingreso = Integer.parseInt(edtIngreso.getText().toString());
                datos1.setIdmonto(Integer.parseInt(id));
                datos1.setFecha(fecha);
                datos1.setIngreso((ingreso));

                if(conexion.modificar(datos1)){
                    Toast.makeText(this, "Registro Modificado Correctamente.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "No se han encontrado resultados para la busqueda especificada.", Toast.LENGTH_SHORT).show();
                }
            }
}
}

