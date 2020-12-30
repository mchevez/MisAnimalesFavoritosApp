package com.example.misanimalesfavoritosapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class ListaAnimalesFavoritos extends AppCompatActivity {

    private ArrayList<Animales> MisAnimales;
    private RecyclerView lstAnimalesOrder;
    private ArrayList<String> allAnimales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_animales_favoritos);

        Bundle xlstanimales = getIntent().getExtras();

        allAnimales = xlstanimales.getStringArrayList("lstAnimales");

        lstAnimalesOrder = (RecyclerView) findViewById(R.id.rvOrderAnimalesFavoritos);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        lstAnimalesOrder.setLayoutManager(llm);
        InicializarAnimales(allAnimales);
        InicializarAdaptador();
    }

    public void InicializarAdaptador(){
        AnimalesAdaptador animales = new AnimalesAdaptador(MisAnimales,ListaAnimalesFavoritos.this);
        lstAnimalesOrder.setAdapter(animales);
    }

    public void InicializarAnimales(ArrayList<String> lstfavoritos){
        Gson gson = new Gson();
        MisAnimales = new ArrayList<Animales>();
        for(String item: lstfavoritos){

            Animales xanimal = gson.fromJson(item,Animales.class);
            MisAnimales.add(xanimal);
        }
        Comparator<Animales> compareById = new Comparator<Animales>() {
            @Override
            public int compare(Animales o1, Animales o2) {
                return o1.getRaiting().compareTo(o2.getRaiting());
            }
        };

        Collections.sort(MisAnimales, compareById);
    }

    @Override
    public boolean onSupportNavigateUp() {
        //Toast.makeText(this,"Regrese",Toast.LENGTH_SHORT).show();

        Intent i = new Intent(this, MainActivity.class);

        i.putStringArrayListExtra("lstAnimales", allAnimales);
        startActivity(i);

        return true;
        //return super.onSupportNavigateUp();
        // Accion de almacenar data
    }
}