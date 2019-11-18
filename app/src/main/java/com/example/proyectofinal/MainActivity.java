package com.example.proyectofinal;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.util.Patterns;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout tiEmail, tiPassword;
    private EditText etEmail, etPassword;
    private Button btnLogin;

    View focusView = null;
    boolean estado_correo;
    boolean estado_password;

    Mantenimiento_Usuarios manto = new Mantenimiento_Usuarios();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);

        tiEmail = (TextInputLayout)findViewById(R.id.tiEmail);
        tiPassword = (TextInputLayout)findViewById(R.id.tiPassword);

        btnLogin = (Button) findViewById(R.id.btnLogin);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement



        return super.onOptionsItemSelected(item);
    }

    public void Entrar(View view) {
        if (etEmail.getText().toString().length() == 0) {
            etEmail.setError("Campo obligatorio");
            estado_correo = false;
        } else {
            estado_correo = true;
        }
        if (etPassword.getText().toString().length() == 0) {
            etPassword.setError("Campo obligatorio");
            estado_password = false;
        } else {
            estado_password = true;
        }

        if (estado_correo && estado_password) {

            Intent i = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(i);
        }
    }
   /* public void login(View v){

        if (Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches() == false) {
            //mEmail.setBackgroundColor(Color.GREEN);
            etEmail.setText(null);
            tiEmail.setError("Correo invalido.");
            focusView = etEmail;
            etEmail.requestFocus();
            estado_correo = false;
        } else {
            estado_correo = true;
            tiEmail.setError(null);
        }


        if (estado_correo == true && (!etPassword.getText().toString().isEmpty())) {
            String correo = etEmail.getText().toString();
            String pass = etPassword.getText().toString();
            manto.verificarSesion(MainActivity.this, correo, pass);
            limpiar();
        }

    }
*/
    private void limpiar(){
        etEmail.setText(null);
        etPassword.setText(null);
        etEmail.requestFocus();
    }
}


