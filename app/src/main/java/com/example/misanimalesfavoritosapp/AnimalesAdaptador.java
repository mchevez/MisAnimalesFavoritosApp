package com.example.misanimalesfavoritosapp;

import android.app.Activity;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AnimalesAdaptador extends  RecyclerView.Adapter<AnimalesAdaptador.AnimalesViewHolder>{

    public AnimalesAdaptador(ArrayList<Animales> misAnimales, Activity activity) {
        this.misAnimales = misAnimales;
        this.activity = activity;
    }
    Activity activity;
    ArrayList<Animales> misAnimales;

    //Inflara el layout y lo pasara al viewHolder para que el obtenga los views
    @NonNull
    @Override
    public AnimalesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_animales,parent,false);
        return new AnimalesViewHolder(v);
    }

    //ASOCIA CADA ELEMENTO DE LA LISTA CON CADA VIEW
    @Override
    public void onBindViewHolder(@NonNull final AnimalesViewHolder holder, int position) {
        final Animales animal = misAnimales.get(position);

        holder.imvFotoAnimal.setImageResource(animal.getFoto());
        holder.tvNombreAnimal.setText(animal.getNombreAnimal());
        holder.tvRaiting.setText(String.valueOf(animal.getRaiting()));

        holder.imvLikeHueso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer totallike = animal.getRaiting()+1;
                animal.setRaiting(totallike);


                holder.tvRaiting.setText(String.valueOf(animal.getRaiting()));

                Toast.makeText(activity,"HOLA SOY: "+animal.getNombreAnimal(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() { //Cantidad de elementos que contiene mi lista de contactos
        return misAnimales.size();
    }

    public static class AnimalesViewHolder extends RecyclerView.ViewHolder{

        private ImageView imvFotoAnimal;
        private ImageView imvLikeHueso;
        private TextView tvNombreAnimal;
        private TextView tvRaiting;
        public AnimalesViewHolder(@NonNull View itemView) {
            super(itemView);

            imvFotoAnimal = (ImageView) itemView.findViewById(R.id.imvFotoAnimal);
            imvLikeHueso = (ImageView) itemView.findViewById(R.id.imvLikeHueso);
            tvNombreAnimal = (TextView) itemView.findViewById(R.id.tvNombreAnimal);
            tvRaiting = (TextView) itemView.findViewById(R.id.tvRaiting);
        }
    }
}
