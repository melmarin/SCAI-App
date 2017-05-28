package com.ucr.dirunoparaiso.scai;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.ucr.dirunoparaiso.scai.*;

import java.util.HashMap;

/**
 * Created by melma on 28/5/2017.
 */

public class Activo extends AppCompatActivity implements View.OnClickListener {
    //Defining views
    private EditText editTextNum;
    private EditText editTextNom;
    private EditText editTextDes;
    private Spinner spinnerDep;
    private EditText editTextDon;
    private EditText editTextAdq;
    private EditText editTextAct;

    private Button buttonAdd;
    //private Button buttonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activo);

        //Initializing views
        editTextNum = (EditText) findViewById(R.id.editTextNum);
        editTextNom = (EditText) findViewById(R.id.editTextNom);
        editTextDes = (EditText) findViewById(R.id.editTextDes);
        spinnerDep = (Spinner) findViewById(R.id.spinnerDep);
        editTextDon = (EditText) findViewById(R.id.editTextDon);
        editTextAdq = (EditText) findViewById(R.id.editTextAdq);
        editTextAct = (EditText) findViewById(R.id.editTextAct);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        //buttonView = (Button) findViewById(R.id.buttonView);

        //Setting listeners to button
        buttonAdd.setOnClickListener(this);
        //buttonView.setOnClickListener(this);
    }

    private void addActivo(){

        final String numeroSerie = editTextNum.getText().toString().trim();
        final String nombre = editTextNom.getText().toString().trim();
        final String descripcion = editTextDes.getText().toString().trim();
        final String donadoPor = editTextDon.getText().toString().trim();
        final String departamento = String.valueOf(spinnerDep.getSelectedItem());
        final String valorAdquisicion = editTextAdq.getText().toString().trim();
        final String valorActual = editTextAct.getText().toString().trim();

        class AddActivo extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Activo.this,"Agregando","Espere...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(Activo.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Configuracion.KEY_ACTIVO_NUM, numeroSerie);
                params.put(Configuracion.KEY_ACTIVO_NOMBRE, nombre);
                params.put(Configuracion.KEY_ACTIVO_DES, descripcion);
                params.put(Configuracion.KEY_ACTIVO_DON, donadoPor);
                params.put(Configuracion.KEY_ACTIVO_DEP, departamento);
                params.put(Configuracion.KEY_ACTIVO_ADQ, valorAdquisicion);
                params.put(Configuracion.KEY_ACTIVO_ACT, valorActual);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Configuracion.URL_ADD_ACTIVO, params);
                return res;
            }
        }

        AddActivo addActivo = new AddActivo();
        addActivo.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonAdd){
            addActivo();
        }

        /*if(v == buttonView){
            startActivity(new Intent(this,ViewAllEmployee.class));
        }*/
    }

}//end class
