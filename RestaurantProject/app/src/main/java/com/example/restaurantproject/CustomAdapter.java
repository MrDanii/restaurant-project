package com.example.restaurantproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.restaurantproject.modelo.Alimento;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<Alimento> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombre;
        TextView tvPrecio;
        ImageView imageViewIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tvNombre = itemView.findViewById(R.id.lista_alimentos_nombre);
            this.tvPrecio = itemView.findViewById(R.id.lista_alimentos_precio);
            this.imageViewIcon = itemView.findViewById(R.id.lista_alimentos_imagen);
        }
    }

    public CustomAdapter(ArrayList<Alimento> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_alimentos_layout, parent, false);

        view.setOnClickListener(ListaAlimentosActivity.handlerOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.tvNombre;
        TextView textViewVersion = holder.tvPrecio;
        ImageView imageView = holder.imageViewIcon;
        int idImageResource;

        textViewName.setText(dataSet.get(listPosition).getNombre());
        textViewVersion.setText(String.valueOf(dataSet.get(listPosition).getPrecio()));
        idImageResource = dataSet.get(listPosition).getCategoria_idCategoria();
        switch (idImageResource) {
            case 1:
                imageView.setImageResource(R.drawable.imagen_comida_1);
                break;
            case 2:
                imageView.setImageResource(R.drawable.imagen_bebida_1);
                break;
            case 3:
                imageView.setImageResource(R.drawable.imagen_postre_1);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
