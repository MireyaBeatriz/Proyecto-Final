package com.example.proyectofinal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListaGasto extends AppCompatActivity {
     String vista = "";
    ArrayList<GastosDto> gastoList;
    ListView listViewGasto;
    ArrayAdapter adaptador;
    SearchView searchView;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter adapter;
    String[] version = {"Aestro","Blender","CupCake","Donut","Eclair","Froyo","GingerBread","HoneyComb","IceCream Sandwich", "Jelly Bean","Kitkat","Lolipop","Marshmallow","Nought","Oreo"};
    Conexion conexion = new Conexion(this);
    GastosDto datos = new GastosDto();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_gasto);


        listViewGasto= (ListView) findViewById(R.id.listViewGasto);
        searchView = (SearchView) findViewById(R.id.searchView);
        //searchView = findViewById(R.id.searchView);
        // conexion.consultaListaArticulos();

        //ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaArticulos);
        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, conexion.consultaListagasto1());
        listViewGasto.setAdapter(adaptador);

        /* list = new ArrayList<>();
         for (int i = 0;i<version.length;i++){ list.add(version[i]);
         } adapter = new ArrayAdapter(ListViewArticulos.this,android.R.layout.simple_list_item_1,list);
        listViewPersonas.setAdapter(adapter); */

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override public boolean onQueryTextSubmit(String s) {
                return false; }

            @Override public boolean onQueryTextChange(String s) { //if(conexion.consultaListaArticulos1().contains(s)){ /* if(list.contains(s)){ adapter.getFilter().filter(s);
                // } return true; } */
                String text = s;
                adaptador.getFilter().filter(text);
                return false;
            }
        });
        listViewGasto.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override public void onItemClick(AdapterView<?> parent, View view, int pos, long l) {
                String informacion="Id: "
                        +conexion.consultaListaGasto().get(pos).getIdgasto()+"\n";
                informacion+="Descripción:"
                        +conexion.consultaListaGasto().get(pos).getEt_descripcion()+"\n";
                informacion+="Fecha: "
                        +conexion.consultaListaGasto().get(pos).getEt_fecha()+"\n";
                informacion+="Monto: "
                        +conexion.consultaListaGasto().get(pos).getEt_monto();
                GastosDto gasto = conexion.consultaListaGasto().get(pos);
                Intent intent = new Intent(ListaGasto.this, DetalleGasto.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("gasto", gasto);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        //al dar click normal
        listViewGasto.setOnItemLongClickListener(new  AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long l) {

                GastosDto gasto = conexion.consultaListaGasto().get(pos);

                Intent i = new Intent(ListaGasto.this, EditarGasto.class);
                startActivity(i);

                //crear el bundle
                Bundle bundle = new Bundle();
                bundle.putSerializable("gasto", gasto);
                i.putExtras(bundle);
                startActivity(i);

                return true;
            }
        });

    }
}
