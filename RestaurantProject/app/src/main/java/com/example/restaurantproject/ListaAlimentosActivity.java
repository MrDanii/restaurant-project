package com.example.restaurantproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.restaurantproject.modelo.Alimento;
import com.example.restaurantproject.persistencia.RestaurantContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListaAlimentosActivity extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private ArrayList<Alimento> alimentosData;
    static View.OnClickListener handlerOnClickListener;

    //Servicio Web
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alimentos);

        inicializarComponentes();
    }

    private synchronized void inicializarComponentes() {
        alimentosData = new ArrayList<>();

        handlerOnClickListener = new HandlerOnClickListener(this);

        recyclerView = findViewById(R.id.alimentos_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //Llamamos el servicio web, para poder llenar el ArrayList con nuestros alimentos de la base de datos

        servicioListaAlimentos("http://" + ConstantesConf.IPV4 + "/restaurantmovil/getAlimentos.php", recyclerView);

        poblarAlimentosData(alimentosData);
        adapter = new CustomAdapter(alimentosData);
        recyclerView.setAdapter(adapter);

    }

    // Por alguna razón la petición get es exitosa, pero no se guardan en el arraylist, hasta añadir manualmente varios
    private void poblarAlimentosData(ArrayList<Alimento> data) {
        alimentosData.add(new Alimento(1, "Milanesa de res", 30));
        alimentosData.add(new Alimento(1, "Corte Rib eye Roll", 80));
        alimentosData.add(new Alimento(1, "Tacos de chorizo", 10));
        alimentosData.add(new Alimento(1, "Tacos de bisteck", 10));
        alimentosData.add(new Alimento(1, "Tacos de constilla", 10));
        alimentosData.add(new Alimento(1, "Tacos de pastor", 10));
        alimentosData.add(new Alimento(1, "Birria", 40));
        alimentosData.add(new Alimento(1, "Chistorra", 35));
        alimentosData.add(new Alimento(1, "Sandwich club", 30));
        alimentosData.add(new Alimento(1, "Parrilla simple", 50));

        alimentosData.add(new Alimento(2, "Refresco", 45));
        alimentosData.add(new Alimento(2, "Jugo de manzana", 40));
        alimentosData.add(new Alimento(2, "Jugo de naranja", 40));
        alimentosData.add(new Alimento(2, "Limonada", 50));
        alimentosData.add(new Alimento(2, "Naranjada", 50));
        alimentosData.add(new Alimento(2, "Agua", 20));
        alimentosData.add(new Alimento(2, "Café Expresso", 20));
        alimentosData.add(new Alimento(2, "Café Americano", 20));
        alimentosData.add(new Alimento(2, "Malteada de fresa", 60));
        alimentosData.add(new Alimento(2, "Malteada de chocolate", 60));

        alimentosData.add(new Alimento(3, "Helado Napolitano", 25));
        alimentosData.add(new Alimento(3, "Flan", 25));
        alimentosData.add(new Alimento(3, "Crepas", 30));
        alimentosData.add(new Alimento(3, "Arroz con leche", 25));
        alimentosData.add(new Alimento(3, "Fresas con crema", 25));
        alimentosData.add(new Alimento(3, "Fresas con crema", 25));
        alimentosData.add(new Alimento(3, "Pay de queso", 30));
        alimentosData.add(new Alimento(3, "Mousse de chocolate", 25));
        alimentosData.add(new Alimento(3, "Pastel de fresa", 40));
        alimentosData.add(new Alimento(3, "Pastel de vainilla", 40));
        alimentosData.add(new Alimento(3, "Pastel de chocolate", 40));
    }

    private class HandlerOnClickListener implements View.OnClickListener {

        private final Context context;

        private HandlerOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View view) {
            // Datos del alimento
            int selectedItemPosition = recyclerView.getChildPosition(view);
            RecyclerView.ViewHolder viewHolder
                    = recyclerView.findViewHolderForPosition(selectedItemPosition);

            TextView tvNombreAlimento = viewHolder.itemView.findViewById(R.id.lista_alimentos_nombre);
            String nombreAlimento = tvNombreAlimento.getText().toString();

            //Terminar la actividad y devolver el alimento
            Intent returnIntent = new Intent();
            returnIntent.putExtra("nombre_alimento", nombreAlimento);
            setResult(ConstantesConf.RESULT_OK, returnIntent);
            finish();
        }
    }

    private void servicioListaAlimentos(String URL, View view) {
        JsonArrayRequest jsonArrayRequest = (JsonArrayRequest) new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject;
                Alimento alimentoActual;

                int idCategoria;
                String nombre;
                float precio;

                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        idCategoria = Integer.parseInt(jsonObject.getString(RestaurantContract.TablaAlimento.COLUMN_ID_CATEGORIA));
                        nombre = jsonObject.getString(RestaurantContract.TablaAlimento.COLUMN_NOMBRE);
                        precio = Float.parseFloat(jsonObject.getString(RestaurantContract.TablaAlimento.COLUMN_PRECIO));
                        alimentoActual = new Alimento(idCategoria, nombre, precio);

                        alimentosData.add(alimentoActual);

                    } catch (JSONException e) {
                        Log.e("Lista Alimentos", e.toString());
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Lista Alimentos", error.toString());
                Toast.makeText(getApplicationContext(),
                        "No se encontraron alimentos", Toast.LENGTH_SHORT).show();
            }
        }).setRetryPolicy(new DefaultRetryPolicy(
                ConstantesConf.DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue = Volley.newRequestQueue(view.getContext());
        requestQueue.add(jsonArrayRequest);
    }
}
