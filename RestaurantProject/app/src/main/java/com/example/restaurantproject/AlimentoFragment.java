package com.example.restaurantproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import com.example.restaurantproject.modelo.Alimento;
import com.example.restaurantproject.persistencia.RestaurantContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AlimentoFragment extends Fragment {

    final String IPV4 = "192.168.0.12";
    static final int DEFAULT_TIMEOUT = 15000;

    private EditText etNombre, etPrecio;
    private Spinner spinnerCategoria;
    private int indexSpinnerCategoria;
    private Button btnRegistrar, btnModificar, btnBuscar, btnEliminar, btnReporte;

    //Variables para el servicio Web
    RequestQueue requestQueue;

    //Objetos modelo
    private Alimento alimento;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_alimento, container, false);

    }

    /*
        Nota #1: es mejor utilizar este método para asegurarnos de que la vista (fragment) ha sido
        totalmente creada y cargada. Evitando que nuestra aplicacion crashee
        Nota #2: este método se llama inmediatamente del método "onCreateView(... )"
    */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Aqui inicializamos cosas
        inicializarComponentes(view, savedInstanceState);
    }

    public void inicializarComponentes(View view, Bundle savedInstanceState) {
        //Inicialización EditText
        etNombre = view.findViewById(R.id.alimento_nombre);
        etPrecio = view.findViewById(R.id.alimento_precio);

        //Inicialicación Spinner
        spinnerCategoria = view.findViewById(R.id.alimento_spinner_categoria);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.alimento_categorias, android.R.layout.simple_spinner_item);
        //Espicificamos el tipo de layout, cuando se muestran las opciones
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapter);

        //Inicialización Button
        btnRegistrar = view.findViewById(R.id.alimento_btn_registrar);
        btnModificar = view.findViewById(R.id.alimento_btn_modificar);
        btnBuscar = view.findViewById(R.id.alimento_btn_buscar);
        btnEliminar = view.findViewById(R.id.alimento_btn_eliminar);
        btnReporte = view.findViewById(R.id.alimento_btn_reporte);

        //Eventos
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                servicioRegistro("http://" + IPV4 + ":80/restaurantmovil/insertarAlimento.php", view);
            }
        });

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                servicioRegistro("http://" + IPV4 + ":80/restaurantmovil/modificarAlimento.php", view);
            }
        });
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = etNombre.getText().toString();
                servicioBuscar("http://" + IPV4 + ":80/restaurantmovil/buscarAlimento.php?nombre=" + nombre, view);
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                servicioEliminar("http://"+ IPV4 + ":80/restaurantmovil/eliminarAlimento.php", view);
            }
        });
        btnReporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void limpiarCampos(){
        etNombre.setText("");
        etPrecio.setText("");
        spinnerCategoria.setSelection(0);
    }

    // -- * -- * -- * -- * -- * -- * -- * -- * -- * -- * -- *
    // -- * -- * -- * -- Servicios Web -- * -- * -- * -- * --
    // -- * -- * -- * -- * -- * -- * -- * -- * -- * -- * -- *

    private void servicioRegistro(String URL, View view) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getView().getContext(), "Operacion Exitosa", Toast.LENGTH_SHORT).show();
                Log.e("Registro Alimento", "Operacion Exitosa");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getView().getContext(), "Operacion Fallida", Toast.LENGTH_SHORT).show();
                Log.e("Registro Alimento", "Operacion Fallida:\n" + error.toString());

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                int indiceCategoria = spinnerCategoria.getSelectedItemPosition();

                parametros.put(RestaurantContract.TablaAlimento.COLUMN_NOMBRE,
                        etNombre.getText().toString());
                parametros.put(RestaurantContract.TablaAlimento.COLUMN_PRECIO,
                        etPrecio.getText().toString());
                parametros.put(RestaurantContract.TablaAlimento.COLUMN_ID_CATEGORIA,
                        String.valueOf(indiceCategoria + 1));

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
                int indiceSpinnerCat = 1; //Comida
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        etNombre.setText(jsonObject.getString(RestaurantContract.TablaAlimento.COLUMN_NOMBRE));
                        etPrecio.setText(jsonObject.getString(RestaurantContract.TablaAlimento.COLUMN_PRECIO));
                        indiceSpinnerCat = jsonObject.getInt(RestaurantContract.TablaAlimento.COLUMN_ID_CATEGORIA) - 1;
                        spinnerCategoria.setSelection(indiceSpinnerCat);
                    } catch (JSONException e) {
                        Log.e("Buscar Alimento", e.toString());
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Buscar Alimento", error.toString());
                Toast.makeText(getView().getContext(),
                        "No se encontro ningún alimento con ese nombre", Toast.LENGTH_SHORT).show();
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
                Log.e("Registro Alimento", "Operacion Exitosa");
                limpiarCampos();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getView().getContext(), "Operacion Fallida", Toast.LENGTH_SHORT).show();
                Log.e("Registro Alimento", "Operacion Fallida:\n" + error.toString());

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put(RestaurantContract.TablaAlimento.COLUMN_NOMBRE,
                        etNombre.getText().toString());

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(view.getContext());
        requestQueue.add(stringRequest);
    }

    /*
        Let see
        https://www.youtube.com/channel/UCKu7ZnF8n0rBBhIqvo5L0xg
     */

}
