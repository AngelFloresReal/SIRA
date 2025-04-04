package com.utez.edu.sira.servlets;

import com.utez.edu.sira.HelloServlet;
import com.utez.edu.sira.models.Aulas;
import com.utez.edu.sira.models.Building;
import com.utez.edu.sira.utils.MySQLConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet (name = "buildingsandroomsservlet", value = "/buildings-and-rooms-servlet")

public class BuildingsAndRoomsservlet extends HelloServlet {

    Connection connection = MySQLConnection.getConnections();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ResultSet resultado = null;
        Statement sentencia = null;

        ResultSet resultado2 = null;
        Statement sentencia2 = null;
        try {


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


            sentencia2 = connection.createStatement();
            resultado2 = sentencia2.executeQuery("select a.*, e.name as edificio_name from aula a join edificios e on a.id_edificio = e.id_edificio");


            List<Aulas> aulas = new ArrayList<>();

            while (resultado2.next()) {
                Aulas aula = new Aulas();
                aula.setId_aula(resultado2.getInt("id_aula"));
                aula.setName(resultado2.getString("name"));
                aula.setId_edificio(resultado2.getInt("id_edificio"));
                aula.setEdificio_name(resultado2.getString("edificio_name"));
                aulas.add(aula);

            }


            System.out.println("Aulas: " + aulas.size());


            request.setAttribute("aulas", aulas);


            String opcion = request.getParameter("opcion");
            if (opcion != null) {
                if (opcion.equals("1")) {
                    RequestDispatcher rd = request.getRequestDispatcher("principal_pages/buildings.jsp");
                    rd.forward(request, response);
                } else if (opcion.equals("2")) {
                    RequestDispatcher rd = request.getRequestDispatcher("reservas-servlet");
                    rd.forward(request, response);
                }
            }

            RequestDispatcher rd = request.getRequestDispatcher("principal_admin.jsp");
            rd.forward(request, response);

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }


    }
}




