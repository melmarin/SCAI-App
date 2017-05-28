package com.ucr.dirunoparaiso.scai;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class GetDepartamento extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextNombreDepartamento;
    private EditText editTextDescripcionDepartamento;
    private EditText editTextIdDepartamento;
    private Button buttonActualizarDepartamento;
    private String idDepartmento;
    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_departamento);

        Intent intent = getIntent();

        idDepartmento = intent.getStringExtra(Configuracion.DEPARTAMENTO);

        editTextNombreDepartamento = (EditText) findViewById(R.id.editTextNombreDepartamento);
        editTextDescripcionDepartamento = (EditText) findViewById(R.id.editTextDescripcionDepartamento);
        editTextIdDepartamento = (EditText) findViewById(R.id.editTextIdDepartamento);

        buttonActualizarDepartamento = (Button) findViewById(R.id.buttonActualizarDepartamento);

        buttonActualizarDepartamento.setOnClickListener(this);

        editTextIdDepartamento.setText(idDepartmento);

        getDepartamento();
    }

    private void getDepartamento(){
        class ObtenerDepartamento extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(GetDepartamento.this,"Cargando...","Espere...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showDepartamento();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Configuracion.URL_GET_DEPARTAMENTO,idDepartmento);
                return s;
            }
        }
        ObtenerDepartamento od = new ObtenerDepartamento();
        od.execute();
    }

    private void showDepartamento(){
        try {
            JSONObject jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Configuracion.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String idDepartamento = c.getString(Configuracion.TAG_DEPARTAMENTO_ID);
            String nombre = c.getString(Configuracion.TAG_DEPARTAMENTO_NOMBRE);
            String descripcion = c.getString(Configuracion.TAG_DESCRIPCION);

            editTextIdDepartamento.setText(idDepartamento);
            editTextNombreDepartamento.setText(nombre);
            editTextDescripcionDepartamento.setText(descripcion);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateDepartamento(){
        final String nombre = editTextNombreDepartamento.getText().toString().trim();
        final String descripcion = editTextDescripcionDepartamento.getText().toString().trim();
        final String idDepartamento = editTextIdDepartamento.getText().toString().trim();

        class UpdateEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(GetDepartamento.this,"Actualizando...","Espere...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(GetDepartamento.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(Configuracion.KEY_DEPARTAMENTO_NOMBRE,nombre);
                hashMap.put(Configuracion.KEY_DEPARTAMENTO_DESCRIPCION,descripcion);
                hashMap.put(Configuracion.KEY_DEPARTAMENTO_ID,idDepartamento);
                hashMap.put(Configuracion.KEY_DEPARTAMENTO_ID_USUARIO,"1");

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Configuracion.URL_UPDATE_DEPARTAMENTO,hashMap);

                return s;
            }
        }

        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonActualizarDepartamento){
            updateDepartamento();
        }
    }
}
