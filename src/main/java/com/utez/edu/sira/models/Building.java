package com.utez.edu.sira.models;

public class Building {
    private int id_edificio;
    private String name;
    private String Edificio_name;

    public Building() {
        this.id_edificio = id_edificio;
        this.name = name;
        Edificio_name = Edificio_name;
    }

    public int getId_edificio() {
        return id_edificio;
    }

    public void setId_edificio(int id_edificio) {
        this.id_edificio = id_edificio;
    }

    public Building(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEdificio_name() {
        return Edificio_name;
    }

    public void setEdificio_name(String edificio_name) {
        Edificio_name = edificio_name;
    }
}
