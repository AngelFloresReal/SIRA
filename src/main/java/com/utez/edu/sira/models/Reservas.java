package com.utez.edu.sira.models;

import java.time.LocalDateTime;

public class Reservas {
    private int id_reserva;
    private String tittle;
    private String descripcion;
    private int id_usuario;
    private String Usuario_name;
    private LocalDateTime start;
    private LocalDateTime end;
    private int id_edificio;
    private String Edificio_name;
    private int id_aula;
    private String Aula_name;

    public Reservas() {
        this.id_reserva = id_reserva;
        this.tittle = tittle;
        this.descripcion = descripcion;
        this.id_usuario = id_usuario;
        this.start = start;
        this.end = end;
        this.id_edificio = id_edificio;
        this.id_aula = id_aula;
        this.Edificio_name = Edificio_name;
        this.Aula_name = Aula_name;
        this.Usuario_name = Usuario_name;
    }

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsuario_name() {
        return Usuario_name;
    }

    public void setUsuario_name(String usuario_name) {
        Usuario_name = usuario_name;
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

    public String getEdificio_name() {
        return Edificio_name;
    }

    public void setEdificio_name(String edificio_name) {
        Edificio_name = edificio_name;
    }

    public int getId_aula() {
        return id_aula;
    }

    public void setId_aula(int id_aula) {
        this.id_aula = id_aula;
    }

    public String getAula_name() {
        return Aula_name;
    }

    public void setAula_name(String aula_name) {
        Aula_name = aula_name;
    }
}
