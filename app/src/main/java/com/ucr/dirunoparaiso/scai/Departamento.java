package com.ucr.dirunoparaiso.scai;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class Departamento extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextNombreDepartamento;
    private EditText editTextDescripcionDepartamento;

    private Button buttonAgregarDepartamento;
    private Button buttonVerDepartamentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departamento);

        //Initializing views
        editTextNombreDepartamento = (EditText) findViewById(R.id.editTextNombreDepartamento);
        editTextDescripcionDepartamento = (EditText) findViewById(R.id.editTextDescripcionDepartamento);

        buttonAgregarDepartamento = (Button) findViewById(R.id.buttonAgregarDepartamento);
        buttonVerDepartamentos = (Button) findViewById(R.id.buttonVerDepartamentos);

        //Setting listeners to button
        buttonAgregarDepartamento.setOnClickListener(this);
        buttonVerDepartamentos.setOnClickListener(this);
    }

    private void agregarDepartamento(){

        final String nombre = editTextNombreDepartamento.getText().toString().trim();
        final String descripcion = editTextDescripcionDepartamento.getText().toString().trim();

        class AgregarDepartamento extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Departamento.this,"Agregando Departamento...","Espere...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(Departamento.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Configuracion.KEY_DEPARTAMENTO_NOMBRE,nombre);
                params.put(Configuracion.KEY_DEPARTAMENTO_DESCRIPCION,descripcion);
                params.put(Configuracion.KEY_DEPARTAMENTO_ID_USUARIO,"1");


                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Configuracion.URL_ADD_DEPARTAMENTO, params);
                return res;
            }
        }

        AgregarDepartamento agregarDepartamento = new AgregarDepartamento();
        agregarDepartamento.execute();
    }

    @Override
    public void onClick(View v) {
        if (v == buttonAgregarDepartamento){
            agregarDepartamento();
        }
        if (v == buttonVerDepartamentos){
            startActivity(new Intent(this, GetDepartamentos.class));
        }
    }
}
