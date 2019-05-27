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

public class EmpleadoFragment extends Fragment {

    private EditText etNombre, etApellidoP, etApellidoM, etUsuario, etPassword;
    private Spinner spinnerPuesto;
    private Button btnRegistrar, btnModificar, btnBuscar, btnEliminar, btnReporte;

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
