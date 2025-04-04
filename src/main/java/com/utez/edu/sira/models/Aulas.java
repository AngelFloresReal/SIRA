package com.utez.edu.sira.models;

public class Aulas {
    private int id_aula;
    private String name;
    private int id_edificio;
    private String Edificio_name;

    public Aulas() {
        this.id_aula = id_aula;
        this.name = name;
        this.id_edificio = id_edificio;
        this.Edificio_name = Edificio_name;
    }

    public int getId_aula() {
        return id_aula;
    }

    public void setId_aula(int id_aula) {
        this.id_aula = id_aula;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId_edificio() {
        return id_edificio;
    }

    public void setId_edificio(int id_edificio) {
        this.id_edificio = id_edificio;
    }

    public String getEdificio_name() {
        return Edificio_name;
    }

    public void setEdificio_name(String edificio_name) {
        Edificio_name = edificio_name;
    }
}
