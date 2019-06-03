package com.example.restaurantproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.restaurantproject.persistencia.RestaurantContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MeseroFragment extends Fragment {

    private TextView tvUsuario, tvNombre;
    private Button btnOrdenes, btnPropinas;
    private String total_Propina;

    //Servicios Web
    RequestQueue requestQueue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mesero, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inicializarComponentes(view, savedInstanceState);
    }

    private void inicializarComponentes(View view, Bundle bundle) {
        tvUsuario = view.findViewById(R.id.mesero_tv_usuario);
        tvNombre = view.findViewById(R.id.mesero_tv_nombre);
        Toast.makeText(getContext(), "usuario: " + getArguments().getString("usuario", "?"),
                Toast.LENGTH_SHORT).show();

        String usuario = "Usuario: " + getArguments().getString("usuario", "");
        String nombre = "Nombre: "+ getArguments().getString("nombre", "");
        tvUsuario.setText(usuario);
        tvNombre.setText(nombre);


        //btnOrdenes = view.findViewById(R.id.mesero_btn_ordenes);
        btnPropinas = view.findViewById(R.id.mesero_btn_propinas);

        //Eventos
        /*
        btnOrdenes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        */
        btnPropinas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = getArguments().getString("usuario", "");

                servicioBuscar("http://" + ConstantesConf.IPV4 +
                        ":80/restaurantmovil/mesero/getPropina.php?clave_empleado=" + usuario, view);
            }
        });
    }

    private void servicioBuscar(String URL, View view) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject;
                String propinas;

                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        propinas = jsonObject.getString("total_propina");
                        if(propinas == null){
                            propinas = "0";
                        }

                        Toast.makeText(getContext(), "Total propinas del día:\n" +
                                propinas, Toast.LENGTH_SHORT).show();

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
                        "No hay propinas registras en el día :(", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(view.getContext());
        requestQueue.add(jsonArrayRequest);
    }
}
