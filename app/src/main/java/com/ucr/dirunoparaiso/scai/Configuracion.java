package com.ucr.dirunoparaiso.scai;

/**
 * Created by Rodrigo on 27/05/2017.
 */

public class Configuracion {

    //Address of our scripts of the CRUD
    public static final String URL_ADD_DEPARTAMENTO="http://pruebasucr2017.esy.es/createDepartamento.php";
    public static final String URL_GET_DEPARTAMENTOS = "http://pruebasucr2017.esy.es/getDepartamentos.php";
    public static final String URL_GET_DEPARTAMENTO = "http://pruebasucr2017.esy.es/getDepartamento.php?id_departamento=";
    public static final String URL_UPDATE_DEPARTAMENTO = "http://pruebasucr2017.esy.es/updateDepartamento.php";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_DEPARTAMENTO_ID = "id_departamento";
    public static final String KEY_DEPARTAMENTO_ID_USUARIO = "id_usuario";
    public static final String KEY_DEPARTAMENTO_NOMBRE = "nombre";
    public static final String KEY_DEPARTAMENTO_DESCRIPCION = "descripcion";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_DEPARTAMENTO_ID = "id_departamento";
    public static final String TAG_DEPARTAMENTO_ID_USUARIO = "id_usuario";
    public static final String TAG_DEPARTAMENTO_NOMBRE = "nombre";
    public static final String TAG_DESCRIPCION = "descripcion";

    //departamento id to pass with intent
    public static final String DEPARTAMENTO = "departamento";


}
