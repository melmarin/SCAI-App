package com.ucr.dirunoparaiso.scai;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.HashMap;
public class Login extends AppCompatActivity implements View.OnClickListener {

    //Defining views
    private EditText editTextUsuario;
    private EditText editTextClave;
    private Button buttonIniciarSesion;
    private String usuario;
    private String clave;
    // La respuesta del JSON es
    private String JSON_STRING;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initializing views
        editTextUsuario = (EditText) findViewById(R.id.editTextUsuario);
        editTextClave = (EditText) findViewById(R.id.editTextClave);

        buttonIniciarSesion = (Button) findViewById(R.id.buttonIniciarSesion);

        //Setting listeners to button
        buttonIniciarSesion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonIniciarSesion) {
            attemptLogin();
        }
    }

    //Login
    private void attemptLogin(){

        final String usuario = editTextUsuario.getText().toString().trim();
        final String password = editTextClave.getText().toString().trim();

        class AttemptLogin extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Login.this,"Iniciando sesión...","Espere...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(Login.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                int success;
                HashMap<String,String> params = new HashMap<>();
                params.put(Configuracion.KEY_USUARIO,usuario);
                params.put(Configuracion.KEY_CLAVE,password);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(Configuracion.URL_LOGIN, params);
                try {
                    JSONObject json = new JSONObject(s);
                    Log.d("Login attemp",json.toString());
                    success = json.getInt(TAG_SUCCESS);
                    if (success == 1){
                        Intent intent = new Intent(Login.this, Producto.class);
                        finish();
                        startActivity(intent);
                        return json.getString(TAG_MESSAGE);

                    }
                    return json.getString(TAG_MESSAGE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

               return null;
            }
        }

        AttemptLogin attemptLogin = new AttemptLogin();
        attemptLogin.execute();
    }

}

