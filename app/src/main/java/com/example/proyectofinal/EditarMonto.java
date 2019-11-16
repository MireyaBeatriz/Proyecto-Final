package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditarMonto extends AppCompatActivity {

    private Button btnEditar;
    private EditText edtIdmonto, edtFecha, edtIngreso;

    MontoInicial datos = new MontoInicial();
    Conexion conexion = new Conexion(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_monto);

        btnEditar = findViewById(R.id.btnEditar);
        edtIdmonto = findViewById(R.id.edtIdmonto);
        edtFecha = findViewById(R.id.edtFecha);
        edtIngreso = findViewById(R.id.edtIngreso);

    }

    public void Editar(View v) {
        if (edtFecha.equals("") || edtIngreso.equals("")) {
            Toast.makeText(datos, "Fecha e Ingreso.\\nSON OBLIGATORIOS", Toast.LENGTH_SHORT).show();

        } else {
            SQLiteDatabase bd = conexion.getWritableDatabase();
            String idmonto = edtIdmonto.getText().toString();
            String fecha = edtFecha.getText().toString();
            String ingreso = edtIngreso.getText().toString();


            ContentValues registro = new ContentValues();
            registro.put("idmonto", idmonto);
            registro.put("fecha", fecha);
            registro.put("ingreso", ingreso);


            //bd.insert("empleado", null, registro);
            bd.update("monto", registro, "idmonto=" + idmonto, null);
            bd.close();

            edtFecha.requestFocus();
            Toast.makeText(datos, "Monto modificado", Toast.LENGTH_SHORT).show();

        }
    }
}

