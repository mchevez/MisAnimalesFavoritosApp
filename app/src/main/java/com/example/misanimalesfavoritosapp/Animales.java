package com.example.misanimalesfavoritosapp;

import java.util.ArrayList;

public class Animales implements Comparable<Animales>{

    private String NombreAnimal;
    private int Foto;
    private Integer Raiting;

    public Animales(int foto, String nombreAnimal,  Integer raiting)
    {
        NombreAnimal = nombreAnimal;
        Foto = foto;
        Raiting = raiting;
    }

    public String getNombreAnimal() {
        return NombreAnimal;
    }

    public void setNombreAnimal(String nombreAnimal) {
        NombreAnimal = nombreAnimal;
    }

    public int getFoto() {
        return Foto;
    }

    public void setFoto(int foto) {
        Foto = foto;
    }

    public Integer getRaiting() {
        return Raiting;
    }

    public void setRaiting(Integer raiting) {
        Raiting = raiting;
    }

    @Override
    public int compareTo(Animales o) {
        return this.getRaiting().compareTo(o.getRaiting());
    }

    public static ArrayList<String> allanimales(ArrayList<Animales> xAnimales)
    {
        ArrayList<String> lstnombres = new ArrayList<String>();

        for(Animales item: xAnimales){

            lstnombres.add(item.getNombreAnimal());
        }
        return lstnombres;
    }
}
