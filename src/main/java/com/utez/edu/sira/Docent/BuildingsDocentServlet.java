package com.utez.edu.sira.Docent;

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

@WebServlet (name = "buildingsdocentservlet", value = "/buildings-docent-servlet")
public class BuildingsDocentServlet extends HelloServlet {
    Connection connection = MySQLConnection.getConnections();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            String building_id = request.getParameter("buildingId");


            Statement sentencia = connection.createStatement();
            ResultSet resultado = sentencia.executeQuery("select * from edificios");
            ;

            List<Building> buildings = new ArrayList<>();

            while (resultado.next()) {
                Building building = new Building();
                building.setId_edificio(resultado.getInt("id_edificio"));
                building.setName(resultado.getString("name"));
                buildings.add(building);
            }

            System.out.println("Edificios " + buildings.size());


            request.setAttribute("buildings", buildings);


            Statement sentencia2 = connection.createStatement();
            ResultSet resultado2 = sentencia2.executeQuery("select * from aula where id_edificio=" + building_id);


            List<Aulas> aulas = new ArrayList<>();

            while (resultado2.next()) {
                Aulas aula = new Aulas();
                aula.setId_aula(resultado2.getInt("id_aula"));
                aula.setName(resultado2.getString("name"));
                aula.setId_edificio(resultado2.getInt("id_edificio"));
                aulas.add(aula);

            }


            System.out.println("Aulas: " + aulas.size());


            request.setAttribute("aulas", aulas);


            String opcion = request.getParameter("opcion");
            if (opcion != null) {
                if (opcion.equals("1")) {
                    RequestDispatcher rd = request.getRequestDispatcher("eventos-docent-servlet");
                    rd.forward(request, response);
                }else if (opcion.equals("2")) {
                    RequestDispatcher rd = request.getRequestDispatcher("principal_docent.jsp");
                    rd.forward(request, response);
                }


            }else {
                RequestDispatcher rd = request.getRequestDispatcher("select-docent.jsp");
                rd.forward(request, response);
            }



            }catch (Exception e){
            e.printStackTrace();
        }

    }

}