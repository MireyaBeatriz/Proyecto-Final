package com.example.proyectofinal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceControl;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class ListaMonto extends AppCompatActivity {
    boolean estadoDelete;
    ListView listViewMonto;
    ArrayList<MontoDto> montoList;
     ArrayAdapter adaptador;
    SearchView searchView;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter adapter;
    String[] version = {"Aestro","Blender","CupCake","Donut","Eclair","Froyo","GingerBread","HoneyComb","IceCream Sandwich", "Jelly Bean","Kitkat","Lolipop","Marshmallow","Nought","Oreo"};
    Conexion conexion = new Conexion(this);
    MontoDto datos = new MontoDto();



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_lista_monto);

            listViewMonto = (ListView) findViewById(R.id.listViewMonto);
            searchView = (SearchView) findViewById(R.id.searchView);
            //searchView = findViewById(R.id.searchView);
            // conexion.consultaListaArticulos();

            //ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaArticulos);
            adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, conexion.consultaListaMonto1());
            listViewMonto.setAdapter(adaptador);

        /* list = new ArrayList<>();
         for (int i = 0;i<version.length;i++){ list.add(version[i]);
         } adapter = new ArrayAdapter(ListViewArticulos.this,android.R.layout.simple_list_item_1,list);
        listViewPersonas.setAdapter(adapter); */

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) { //if(conexion.consultaListaArticulos1().contains(s)){ /* if(list.contains(s)){ adapter.getFilter().filter(s);
                    // } return true; } */
                    String text = s;
                    adaptador.getFilter().filter(text);
                    return false;
                }
            });



            listViewMonto.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int pos, long l) {
                    String informacion = "Id: "
                            + conexion.consultaListaMonto().get(pos).getFecha() + "\n";
                    informacion += "Fecha: "
                            + conexion.consultaListaMonto().get(pos).getFecha() + "\n";
                    informacion += "Ingreso: "
                            + conexion.consultaListaMonto().get(pos).getIngreso();

                    MontoDto monto = conexion.consultaListaMonto().get(pos);


                    Intent intent = new Intent(ListaMonto.this, DetalleMonto.class);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("monto", monto);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });

            //al dar click largo
            listViewMonto.setOnItemLongClickListener(new  AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long l) {


                    MontoDto monto = conexion.consultaListaMonto().get(pos);

                    Intent i = new Intent(ListaMonto.this, EditarMonto.class);
                    startActivity(i);

                    //crear el bundle
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("monto", monto);
                    i.putExtras(bundle);
                    startActivity(i);

                    return true;
                }
            });

        }

    }

