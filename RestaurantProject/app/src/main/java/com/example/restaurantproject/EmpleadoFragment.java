package com.example.restaurantproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.restaurantproject.persistencia.RestaurantContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EmpleadoFragment extends Fragment {

    private EditText etNombre, etApellidoP, etApellidoM, etUsuario, etPassword;
    private Spinner spinnerPuesto;
    private Button btnRegistrar, btnModificar, btnBuscar, btnEliminar, btnReporte;

    //Variables para el servicio web
    RequestQueue requestQueue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_empleado, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inicializarComponentes(view, savedInstanceState);
    }

    private void inicializarComponentes(View view, Bundle bundle) {
        etNombre = view.findViewById(R.id.empleado_nombre);
        etApellidoP = view.findViewById(R.id.empleado_apellido_p);
        etApellidoM = view.findViewById(R.id.empleado_apellido_M);
        etUsuario = view.findViewById(R.id.empleado_usuario);
        etPassword = view.findViewById(R.id.empleado_password);

        spinnerPuesto = view.findViewById(R.id.empleado_spinner_puesto);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.empleado_puesto, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPuesto.setAdapter(adapter);

        btnRegistrar = view.findViewById(R.id.empleado_btn_registrar);
        btnModificar = view.findViewById(R.id.empleado_btn_modificar);
        btnBuscar = view.findViewById(R.id.empleado_btn_buscar);
        btnEliminar = view.findViewById(R.id.empleado_btn_eliminar);
        btnReporte = view.findViewById(R.id.empleado_btn_reporte);

        //Eventos
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                servicioRegMod("http://" + ConstantesConf.IPV4 +
                        ":80/restaurantmovil/empleado/insertarEmpleado.php", view);
            }
        });
        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                servicioRegMod("http://" + ConstantesConf.IPV4 +
                        ":80/restaurantmovil/empleado/modificarEmpleado.php", view);
            }
        });
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = etUsuario.getText().toString();
                usuario = usuario.replace(" ", "%20");
                servicioBuscar("http://" + ConstantesConf.IPV4 +
                        ":80/restaurantmovil/empleado/buscarEmpleado.php?clave_empleado=" + usuario, view);
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                servicioEliminar("http://"+ ConstantesConf.IPV4 +
                        ":80/restaurantmovil/empleado/eliminarEmpleado.php", view);
            }
        });
        btnReporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void limpiarCampos() {
        etNombre.setText("");
        etApellidoP.setText("");
        etApellidoM.setText("");
        etUsuario.setText("");
        etPassword.setText("");
        spinnerPuesto.setSelection(0);
    }

    /**
     * Metodo que servirá para ejecutar el servicio para registrar y para modificar un
     * registro del empleado
     *
     * @param URL
     * @param view
     */
    private void servicioRegMod(String URL, View view) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getView().getContext(), "Operacion Exitosa", Toast.LENGTH_SHORT).show();
                Log.e("Registro Empleado", "Operacion Exitosa");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getView().getContext(), "Operacion Fallida", Toast.LENGTH_SHORT).show();
                Log.e("Registro Empleado", "Operacion Fallida:\n" + error.toString());

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                String cadenaPuesto = spinnerPuesto.getSelectedItem().toString();

                parametros.put(RestaurantContract.TablaEmpleado.COLUMN_NOMBRE,
                        etNombre.getText().toString());
                parametros.put(RestaurantContract.TablaEmpleado.COLUMN_APELLIDOP,
                        etApellidoP.getText().toString());
                parametros.put(RestaurantContract.TablaEmpleado.COLUMN_APELLIDOM,
                        etApellidoM.getText().toString());
                parametros.put(RestaurantContract.TablaEmpleado.COLUMN_PUESTO,
                        cadenaPuesto);
                parametros.put(RestaurantContract.TablaEmpleado.COLUMN_CLAVE_EMPLEADO,
                        etUsuario.getText().toString());
                parametros.put(RestaurantContract.TablaEmpleado.COLUMN_PASSWORD,
                        etPassword.getText().toString());

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(view.getContext());
        requestQueue.add(stringRequest);
    }

    private void servicioBuscar(String URL, View view) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                int indicePuesto = 1; //Puesto
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);

                        etNombre.setText(jsonObject.getString(RestaurantContract.TablaEmpleado.COLUMN_NOMBRE));
                        etApellidoP.setText(jsonObject.getString(RestaurantContract.TablaEmpleado.COLUMN_APELLIDOP));
                        etApellidoM.setText(jsonObject.getString(RestaurantContract.TablaEmpleado.COLUMN_APELLIDOM));
                        etNombre.setText(jsonObject.getString(RestaurantContract.TablaEmpleado.COLUMN_NOMBRE));
                        etPassword.setText(jsonObject.getString(RestaurantContract.TablaEmpleado.COLUMN_PASSWORD));
                        indicePuesto = (jsonObject.getString(
                                RestaurantContract.TablaEmpleado.COLUMN_PUESTO).equalsIgnoreCase("Mesero") ? 0 : 1);

                        spinnerPuesto.setSelection(indicePuesto);
                    } catch (JSONException e) {
                        Log.e("Buscar Empleado", e.toString());
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Buscar Empleado", error.toString());
                Toast.makeText(getView().getContext(),
                        "No se encontro ningún empleado con ese usuario", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(view.getContext());
        requestQueue.add(jsonArrayRequest);
    }

    private void servicioEliminar(String URL, View view) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getView().getContext(), "Operacion Exitosa", Toast.LENGTH_SHORT).show();
                Log.e("Eliminación Empleado", "Operacion Exitosa");
                limpiarCampos();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getView().getContext(), "Operacion Fallida", Toast.LENGTH_SHORT).show();
                Log.e("Eliminación Empleado", "Operacion Fallida:\n" + error.toString());

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put(RestaurantContract.TablaEmpleado.COLUMN_CLAVE_EMPLEADO,
                        etUsuario.getText().toString());

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(view.getContext());
        requestQueue.add(stringRequest);
    }
}
