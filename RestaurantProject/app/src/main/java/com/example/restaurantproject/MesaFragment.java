package com.example.restaurantproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MesaFragment extends Fragment {

    private ArrayList<Button> btnMesas;
    private int cantidadMesas;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mesa, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cantidadMesas = 30;
        inicializarComponentes(view,savedInstanceState);
    }

    private void inicializarComponentes(View view, Bundle savedInstanceState) {
        //Inicializando botones
        btnMesas = new ArrayList<>();
        btnMesas.add((Button)view.findViewById(R.id.mesas_btn_1));
        btnMesas.add((Button)view.findViewById(R.id.mesas_btn_2));
        btnMesas.add((Button)view.findViewById(R.id.mesas_btn_3));
        btnMesas.add((Button)view.findViewById(R.id.mesas_btn_4));
        btnMesas.add((Button)view.findViewById(R.id.mesas_btn_5));
        btnMesas.add((Button)view.findViewById(R.id.mesas_btn_6));
        btnMesas.add((Button)view.findViewById(R.id.mesas_btn_7));
        btnMesas.add((Button)view.findViewById(R.id.mesas_btn_8));
        btnMesas.add((Button)view.findViewById(R.id.mesas_btn_9));
        btnMesas.add((Button)view.findViewById(R.id.mesas_btn_10));
        btnMesas.add((Button)view.findViewById(R.id.mesas_btn_11));
        btnMesas.add((Button)view.findViewById(R.id.mesas_btn_12));
        btnMesas.add((Button)view.findViewById(R.id.mesas_btn_13));
        btnMesas.add((Button)view.findViewById(R.id.mesas_btn_14));
        btnMesas.add((Button)view.findViewById(R.id.mesas_btn_15));
        btnMesas.add((Button)view.findViewById(R.id.mesas_btn_16));
        btnMesas.add((Button)view.findViewById(R.id.mesas_btn_17));
        btnMesas.add((Button)view.findViewById(R.id.mesas_btn_18));
        btnMesas.add((Button)view.findViewById(R.id.mesas_btn_19));
        btnMesas.add((Button)view.findViewById(R.id.mesas_btn_20));
        btnMesas.add((Button)view.findViewById(R.id.mesas_btn_21));
        btnMesas.add((Button)view.findViewById(R.id.mesas_btn_22));
        btnMesas.add((Button)view.findViewById(R.id.mesas_btn_23));
        btnMesas.add((Button)view.findViewById(R.id.mesas_btn_24));
        btnMesas.add((Button)view.findViewById(R.id.mesas_btn_25));
        btnMesas.add((Button)view.findViewById(R.id.mesas_btn_26));
        btnMesas.add((Button)view.findViewById(R.id.mesas_btn_27));
        btnMesas.add((Button)view.findViewById(R.id.mesas_btn_28));
        btnMesas.add((Button)view.findViewById(R.id.mesas_btn_29));
        btnMesas.add((Button)view.findViewById(R.id.mesas_btn_30));

        for(int i = 0; i < cantidadMesas; i ++){
            btnMesas.get(i).setOnClickListener(new HandlerBtnMesa());
        }
    }

    private class HandlerBtnMesa implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Button btn = (Button) view;
            int numeroMesa = Integer.parseInt(btn.getText().toString());
            Toast.makeText(view.getContext(), "numero mesa: " + numeroMesa, Toast.LENGTH_SHORT).show();
            // mostramos el numero de sillas
        }
    }
}
