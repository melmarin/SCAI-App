package com.ucr.dirunoparaiso.scai;

/**
 * Created by Rodrigo on 27/05/2017.
 */

public class Configuracion {

    //Address of our scripts of the CRUD
    public static final String URL_LOGIN="http://pruebasucr2017.esy.es/login.php";
    public static final String URL_ADD_DEPARTAMENTO="http://pruebasucr2017.esy.es/createDepartamento.php";
    public static final String URL_GET_DEPARTAMENTOS = "http://pruebasucr2017.esy.es/getDepartamentos.php";
    public static final String URL_GET_DEPARTAMENTO = "http://pruebasucr2017.esy.es/getDepartamento.php?id_departamento=";
    public static final String URL_UPDATE_DEPARTAMENTO = "http://pruebasucr2017.esy.es/updateDepartamento.php";
    //ACTIVOS
    public static final String URL_ADD_ACTIVO = "http://pruebasucr2017.esy.es/addActivo.php";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_DEPARTAMENTO_ID = "id_departamento";
    public static final String KEY_DEPARTAMENTO_ID_USUARIO = "id_usuario";
    public static final String KEY_DEPARTAMENTO_NOMBRE = "nombre";
    public static final String KEY_DEPARTAMENTO_DESCRIPCION = "descripcion";
    public static final String KEY_USUARIO = "usuario";
    public static final String KEY_CLAVE = "password";
    //ACTIVOS
    public static final String KEY_ACTIVO_NUM = "numeroSerie";
    public static final String KEY_ACTIVO_NOMBRE = "nombre";
    public static final String KEY_ACTIVO_DES = "descripcion";
    public static final String KEY_ACTIVO_DON = "donadoPor";
    public static final String KEY_ACTIVO_DEP = "departamento";
    public static final String KEY_ACTIVO_ADQ = "valorAdquisicion";
    public static final String KEY_ACTIVO_ACT = "valorActual";
    public static final String KEY_ACTIVO_MOD = "fechaModificacion";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_DEPARTAMENTO_ID = "id_departamento";
    public static final String TAG_DEPARTAMENTO_ID_USUARIO = "id_usuario";
    public static final String TAG_DEPARTAMENTO_NOMBRE = "nombre";
    public static final String TAG_DESCRIPCION = "descripcion";
    public static final String TAG_USUARIO = "usuario";
    public static final String TAG_CLAVE = "password";
    //ACTIVO
    public static final String TAG_ACTIVO_ID = "idActivo";
    public static final String TAG_ACTIVO_NUM = "numeroSerie";
    public static final String TAG_ACTIVO_NOMBRE = "nombre";
    public static final String TAG_ACTIVO_DES = "descripcion";
    public static final String TAG_ACTIVO_DON = "donadoPor";
    public static final String TAG_ACTIVO_DEP = "departamento";
    public static final String TAG_ACTIVO_ADQ = "valorAdquisicion";
    public static final String TAG_ACTIVO_ACT = "valorActual";
    public static final String TAG_ACTIVO_MOD = "fechaModificacion";

    //departamento id to pass with intent
    public static final String DEPARTAMENTO = "departamento";
    public static final String ACTIVO_ID = "id_activo";

}//class
