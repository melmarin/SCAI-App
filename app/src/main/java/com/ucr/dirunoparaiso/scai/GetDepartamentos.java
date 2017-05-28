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
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class GetDepartamentos extends AppCompatActivity implements ListView.OnClickListener, AdapterView.OnItemClickListener {

    private ListView listViewDepartamentos;
    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_departamentos);
        listViewDepartamentos = (ListView) findViewById(R.id.listViewDepartamentos);
        listViewDepartamentos.setOnItemClickListener(this);
        getJSON();
    }

    private void showDepartamento(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Configuracion.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String idDepartamento = jo.getString(Configuracion.TAG_DEPARTAMENTO_ID);
                String nombreDepartamento = jo.getString(Configuracion.TAG_DEPARTAMENTO_NOMBRE);

                HashMap<String,String> employees = new HashMap<>();
                employees.put(Configuracion.TAG_DEPARTAMENTO_ID,idDepartamento);
                employees.put(Configuracion.TAG_DEPARTAMENTO_NOMBRE,nombreDepartamento);
                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                GetDepartamentos.this, list, R.layout.list_item_departamento,
                new String[]{Configuracion.TAG_DEPARTAMENTO_ID, Configuracion.TAG_DEPARTAMENTO_NOMBRE},
                new int[]{R.id.id_departamento, R.id.nombre_departamento});

        listViewDepartamentos.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(GetDepartamentos.this,"Cargando Datos","Espere...",false,false);
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
                String s = rh.sendGetRequest(Configuracion.URL_GET_DEPARTAMENTOS);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, GetDepartamento.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String departamentoID = map.get(Configuracion.TAG_DEPARTAMENTO_ID).toString();
        intent.putExtra(Configuracion.DEPARTAMENTO,departamentoID);
        startActivity(intent);
    }
}
