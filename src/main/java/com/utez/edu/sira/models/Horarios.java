package com.utez.edu.sira.models;

import java.time.LocalDateTime;

public class Horarios {
    private int id_horario;
    private String tittle;
    private LocalDateTime start;
    private LocalDateTime end;
    private int id_edificio;
    private String Edificio_name;
    private int id_aula;
    private String Aula_name;

    public Horarios() {
        this.id_horario = id_horario;
        this.tittle = tittle;
        this.start = start;
        this.end = end;
        this.id_edificio = id_edificio;
        this.id_aula = id_aula;
        this.Edificio_name = Edificio_name;
        this.Aula_name = Aula_name;
    }

    public int getId_horario() {
        return id_horario;
    }

    public void setId_horario(int id_horario) {
        this.id_horario = id_horario;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public int getId_edificio() {
        return id_edificio;
    }

    public void setId_edificio(int id_edificio) {
        this.id_edificio = id_edificio;
    }

    public int getId_aula() {
        return id_aula;
    }

    public void setId_aula(int id_aula) {
        this.id_aula = id_aula;
    }

    public String getEdificio_name() {
        return Edificio_name;
    }

    public void setEdificio_name(String edificio_name) {
        Edificio_name = edificio_name;
    }

    public String getAula_name() {
        return Aula_name;
    }

    public void setAula_name(String aula_name) {
        Aula_name = aula_name;
    }
}
