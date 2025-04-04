package com.utez.edu.sira.models;

public class UsuarioBean {
    private int id_usuario;
    private String first_name;
    private String last_name;
    private String correo;
    private String password;
    private int id_carrera;
    private int rol;
    private String matricula;

    public UsuarioBean(int id_usuario, String first_name, String last_name, String correo, String password, int id_carrera, int rol, String matricula) {
        this.id_usuario = id_usuario;
        this.first_name = first_name;
        this.last_name = last_name;
        this.correo = correo;
        this.password = password;
        this.id_carrera = id_carrera;
        this.rol = rol;
        this.matricula = matricula;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId_carrera() {
        return id_carrera;
    }

    public void setId_carrera(int id_carrera) {
        this.id_carrera = id_carrera;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
