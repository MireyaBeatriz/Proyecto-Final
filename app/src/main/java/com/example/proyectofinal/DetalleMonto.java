package com.example.proyectofinal;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DetalleMonto extends AppCompatActivity {

    private EditText edtdetalle;
    boolean estadodetalle = false;


    Conexion conexion = new Conexion(this);
   DetalleDto datos = new DetalleDto();

    public DetalleMonto() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_monto);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtdetalle = findViewById(R.id.edtdetalle);

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
            edtdetalle.setText("");
            edtdetalle.requestFocus();

            Toast.makeText(this, "Ingrese todos los datos", Toast.LENGTH_SHORT).show();
            //Toast.makeText(getContext(), "Ingrese todos los datos", Toast.LENGTH_SHORT).show();
        }

        public void Guardar(View view) {
            if (edtdetalle.getText().toString().length() == 0) {
                estadodetalle = false;
                edtdetalle.setError("Campo obligatorio");
            } else {
                estadodetalle = true;
            }


            if (estadodetalle) {
                try {
                    // datos.setIdmonto(Integer.parseInt(edtFecha.Null);
                    datos.setDetalle(edtdetalle.getText().toString());

                    //if(conexion.insertardatos(datos)){ //if(conexion.InsertRegister(datos)){
                    if (conexion.InsertDetalle(datos)) {
                        Toast.makeText(this, "Registro agregado satisfactoriamente!", Toast.LENGTH_SHORT).show();
                        Nuevo(view);
                    } else {
                        Toast.makeText(getApplicationContext(), "Error. Ya existe un registro\n" + " detalle: " + edtdetalle.getText().toString(), Toast.LENGTH_LONG).show();
                        Nuevo(view);
                    }
                } catch (Exception e) {
                    Toast.makeText(this, "ERROR. Ya existe.", Toast.LENGTH_SHORT).show();
                }
            }


        }
}
