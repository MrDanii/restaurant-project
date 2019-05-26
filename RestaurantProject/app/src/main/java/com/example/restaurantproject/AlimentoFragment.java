package com.example.restaurantproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AlimentoFragment extends Fragment {

    private EditText etNombre, etPrecio;
    private Spinner spinnerCategoria;
    private Button btnRegistrar, btnModificar, btnBuscar, btnEliminar, btnReporte;

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
                Toast.makeText(view.getContext(), "Elemento ComboBox: " +
                        spinnerCategoria.getSelectedItem(), Toast.LENGTH_SHORT).show();
            }
        });

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnReporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

}
