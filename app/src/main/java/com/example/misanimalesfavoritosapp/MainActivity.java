package com.example.misanimalesfavoritosapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Animales> MisAnimales;
    private RecyclerView lstAnimales;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle xlstanimales = getIntent().getExtras();

        lstAnimales = (RecyclerView) findViewById(R.id.rvAnimales);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        lstAnimales.setLayoutManager(llm);

        if(xlstanimales == null){
            InicializarAnimales();
            //Toast.makeText(this,"SIN DATOS",Toast.LENGTH_SHORT).show();
        }
        else{
            ArrayList<String>  allAnimales = xlstanimales.getStringArrayList("lstAnimales");

            InicializarAnimales(allAnimales);
            //Toast.makeText(this,"CON DATOS",Toast.LENGTH_SHORT).show();
        }

        InicializarAdaptador();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mRefresh:
                //Toast.makeText(this, "Menu Funcionando", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, ListaAnimalesFavoritos.class);

                Gson gson = new Gson();
                ArrayList<String> misanimales = new ArrayList<String>();

                for(Animales xanimal: MisAnimales){
                    misanimales.add(gson.toJson(xanimal));
                }

                i.putStringArrayListExtra("lstAnimales", misanimales);
                startActivity(i);

                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void InicializarAdaptador(){
        AnimalesAdaptador animales = new AnimalesAdaptador(MisAnimales,MainActivity.this);
        lstAnimales.setAdapter(animales);

    }
    public void InicializarAnimales(){
        MisAnimales = new ArrayList<Animales>();

        MisAnimales.add(new Animales(R.drawable.conejo,"Petter",2));
        MisAnimales.add(new Animales(R.drawable.perro,"Tyron",8));
        MisAnimales.add(new Animales(R.drawable.pato,"Rigo",4));
        MisAnimales.add(new Animales(R.drawable.tucan,"Paco",5));
        MisAnimales.add(new Animales(R.drawable.gato,"Susy",7));
    }

    public void InicializarAnimales(ArrayList<String> lstfavoritos){
        Gson gson = new Gson();
        MisAnimales = new ArrayList<Animales>();
        for(String item: lstfavoritos){

            Animales xanimal = gson.fromJson(item,Animales.class);
            MisAnimales.add(xanimal);
        }
    }
}