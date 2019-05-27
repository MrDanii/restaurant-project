package com.example.restaurantproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MeseroFragment extends Fragment {

    private TextView tvNombre, tvNumero;
    private Button btnOrdenes, btnPropinas;

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
        tvNombre = view.findViewById(R.id.mesero_tv_nombre);
        tvNumero = view.findViewById(R.id.mesero_tv_numero);

        btnOrdenes = view.findViewById(R.id.mesero_btn_ordenes);
        btnPropinas = view.findViewById(R.id.mesero_btn_propinas);

        //Eventos
        btnOrdenes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnPropinas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
