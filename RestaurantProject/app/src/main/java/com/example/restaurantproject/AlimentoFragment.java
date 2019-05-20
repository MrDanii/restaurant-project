package com.example.restaurantproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class AlimentoFragment extends Fragment {



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

    public void inicializarComponentes(View view, Bundle savedInstanceState){
        Button botonMensajeAlimento = view.findViewById(R.id.boton_alimento_mensaje);
        botonMensajeAlimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), ":o :O :0", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
