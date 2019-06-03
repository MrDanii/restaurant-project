package com.example.restaurantproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.restaurantproject.modelo.Alimento;
import com.example.restaurantproject.modelo.Empleado;
import com.example.restaurantproject.persistencia.RestaurantContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsuario, etPassword;
    private Button btnLogin;

    //Servicio web para verificar el login correcto
    RequestQueue requestQueue;

    private Empleado empleado;
    private String usuario, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializar();
    }

    private void inicializar(){
        etUsuario = findViewById(R.id.login_et_usuario);
        etPassword = findViewById(R.id.login_et_password);
        btnLogin = findViewById(R.id.login_et_login);
    }

    public synchronized void handlerBtnLogin(View view) {
        // Busqueda de usuario y password en la base de datos
        usuario = etUsuario.getText().toString().replace(" ", "%20");
        password = etPassword.getText().toString().replace(" ", "%20");
        servicioVerificarLogin("http://" + ConstantesConf.IPV4 +
                ":80/restaurantmovil/login/getEmpleado.php?clave_empleado=" + usuario +
                "&password=" + password, view);
        if(empleado != null){
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("usuario", empleado.getClave_empleado());
            intent.putExtra("password", empleado.getContrasena());
            intent.putExtra("nombre", empleado.getNombre() + " " +
                    empleado.getApellido_paterni() + " " + empleado.getApellido_materno());
            intent.putExtra("puesto", empleado.getPuesto());
            empleado = null;
            startActivity(intent);
        }else{
            Toast.makeText(this, "Nombre de usuario o contraseña incorrecto",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void servicioVerificarLogin(String URL, View view){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;

                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);

                        empleado = new Empleado();
                        empleado.setNombre(jsonObject.getString(RestaurantContract.TablaEmpleado.COLUMN_NOMBRE));
                        empleado.setApellido_paterni(jsonObject.getString(RestaurantContract.TablaEmpleado.COLUMN_APELLIDOP));
                        empleado.setApellido_materno(jsonObject.getString(RestaurantContract.TablaEmpleado.COLUMN_APELLIDOM));
                        empleado.setClave_empleado(jsonObject.getString(RestaurantContract.TablaEmpleado.COLUMN_CLAVE_EMPLEADO));
                        empleado.setContrasena(jsonObject.getString(RestaurantContract.TablaEmpleado.COLUMN_PASSWORD));
                        empleado.setPuesto(jsonObject.getString(RestaurantContract.TablaEmpleado.COLUMN_PUESTO));

                    } catch (JSONException e) {
                        Log.e("Login Empleado", e.toString());
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Login Empleado", error.toString());
                Toast.makeText(getApplicationContext(),
                        "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(view.getContext());
        requestQueue.add(jsonArrayRequest);
    }
}
