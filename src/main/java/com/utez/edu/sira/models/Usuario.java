package com.utez.edu.sira.models;

public class Usuario {
    private int id_usuario;
    private String first_name;
    private String last_name;
    private String correo;
    private String matricula;
    private String password;
    private int id_rol;
    private String rol_name;

    public Usuario() {
        this.id_usuario = id_usuario;
        this.first_name = first_name;
        this.last_name = last_name;
        this.correo = correo;
        this.matricula = matricula;
        this.password = password;
        this.id_rol = id_rol;
        this.rol_name = rol_name;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public String getrol_name() {
        return rol_name;
    }

    public void setrol_name(String rol_name) {
        this.rol_name = rol_name;
    }
}
