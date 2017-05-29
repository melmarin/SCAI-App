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
 * Created by ROCIO on 28/5/2017.
 */

public class Producto extends AppCompatActivity implements View.OnClickListener {
    //Defining views
    private EditText editTextNom;
    private EditText editTextDes;
    private EditText editTextPes;
    private EditText editTextPro;
    private EditText editTextMin;
    private EditText editTextAct;

    private Button buttonAdd;
    //private Button buttonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        //Initializing views
        editTextNom = (EditText) findViewById(R.id.editTextNom);
        editTextDes = (EditText) findViewById(R.id.editTextDes);
        editTextPes = (EditText) findViewById(R.id.editTextPes);
        editTextPro = (EditText) findViewById(R.id.editTextPro);
        editTextMin = (EditText) findViewById(R.id.editTextMin);
        editTextAct = (EditText) findViewById(R.id.editTextAct);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        //buttonView = (Button) findViewById(R.id.buttonView);

        //Setting listeners to button
        buttonAdd.setOnClickListener(this);
        //buttonView.setOnClickListener(this);
    }

    private void addProducto(){

        final String nombre = editTextNom.getText().toString().trim();
        final String descripcion = editTextDes.getText().toString().trim();
        final String peso = editTextPes.getText().toString().trim();
        final String proveedor = editTextPro.getText().toString().trim();
        final String cantidadMinima = editTextMin.getText().toString().trim();
        final String cantidadActual = editTextAct.getText().toString().trim();

        class AddProducto extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Producto.this,"Agregando","Espere...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(Producto.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Configuracion.KEY_PRODUCTO_NOMBRE, nombre);
                params.put(Configuracion.KEY_PRODUCTO_DES, descripcion);
                params.put(Configuracion.KEY_PRODUCTO_PESO, peso);
                params.put(Configuracion.KEY_PRODUCTO_PROV, proveedor);
                params.put(Configuracion.KEY_PRODUCTO_MIN, cantidadMinima);
                params.put(Configuracion.KEY_PRODUCTO_ACT, cantidadActual);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Configuracion.URL_ADD_PRODUCTO, params);
                return res;
            }
        }

        AddProducto addProducto = new AddProducto();
        addProducto.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonAdd){
            addProducto();
        }

        /*if(v == buttonView){
            startActivity(new Intent(this,ViewAllEmployee.class));
        }*/
    }
}
