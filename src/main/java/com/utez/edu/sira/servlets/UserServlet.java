package com.utez.edu.sira.servlets;

import com.utez.edu.sira.models.Aulas;
import com.utez.edu.sira.models.Building;
import com.utez.edu.sira.models.Usuario;
import com.utez.edu.sira.utils.MySQLConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@WebServlet (name = "userservlet", value = "/user-servlet")

public class UserServlet extends HttpServlet {
    Connection connection = MySQLConnection.getConnections();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Statement sentencia = connection.createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT u.*, r.rol_name FROM user u join rol r on u.id_rol = r.id_rol");

            List<Usuario> usuarios = new ArrayList<>();

            while (resultado.next()){
                Usuario usuario = new Usuario();
                usuario.setId_usuario(resultado.getInt("id_usuario"));
                usuario.setFirst_name(resultado.getString("first_name"));
                usuario.setLast_name(resultado.getString("last_name"));
                usuario.setCorreo(resultado.getString("correo"));
                usuario.setMatricula(resultado.getString("matricula"));
                usuario.setPassword(resultado.getString("password"));
                usuario.setrol_name(resultado.getString("rol_name"));
                usuarios.add(usuario);

            }

            System.out.println("Usuarios: " + usuarios.size());

            request.setAttribute("usuarios", usuarios);


            sentencia = connection.createStatement();
            resultado = sentencia.executeQuery("select e.*, e.name as edificio_name from edificios e");


            List<Building> buildings = new ArrayList<>();

            while (resultado.next()) {
                Building building = new Building();
                building.setId_edificio(resultado.getInt("id_edificio"));
                building.setName(resultado.getString("name"));
                building.setEdificio_name(resultado.getString("edificio_name"));
                buildings.add(building);
            }

            System.out.println("Edificios " + buildings.size());


            request.setAttribute("buildings", buildings);


            sentencia = connection.createStatement();
            resultado = sentencia.executeQuery("select a.*, e.name as edificio_name from aula a join edificios e on a.id_edificio = e.id_edificio");


            List<Aulas> aulas = new ArrayList<>();

            while (resultado.next()) {
                Aulas aula = new Aulas();
                aula.setId_aula(resultado.getInt("id_aula"));
                aula.setName(resultado.getString("name"));
                aula.setId_edificio(resultado.getInt("id_edificio"));
                aula.setEdificio_name(resultado.getString("edificio_name"));
                aulas.add(aula);

            }


            System.out.println("Aulas: " + aulas.size());


            request.setAttribute("aulas", aulas);



            RequestDispatcher rd = request.getRequestDispatcher("principal_pages/register.jsp");
            rd.forward(request, response);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
