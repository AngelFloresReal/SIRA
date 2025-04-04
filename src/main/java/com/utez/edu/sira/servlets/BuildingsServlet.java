package com.utez.edu.sira.servlets;

import com.google.gson.Gson;
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
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet (name = "buldingsservlet", value = "/buildings-servlet")

public class BuildingsServlet extends HelloServlet {
    Connection connection = MySQLConnection.getConnections();




    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try  {

            String building_id = request.getParameter("buildingId");

            System.out.println(building_id);

            Statement sentencia = connection.createStatement();
            ResultSet resultado = sentencia.executeQuery("select * from edificios");;

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

            String opcion2 = request.getParameter("opcion");
            if (opcion2 != null && opcion2.equals("15")) {
                Gson gson = new Gson();
                String json = gson.toJson(aulas);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
                return;
            }



                String opcion = request.getParameter("opcion");
                if (opcion != null) {
                    if (opcion.equals("1")) {
                        RequestDispatcher rd = request.getRequestDispatcher("principal_pages/buildings.jsp");
                        rd.forward(request, response);
                    } else if (opcion.equals("2")) {
                        RequestDispatcher rd = request.getRequestDispatcher("reservas-servlet");
                        rd.forward(request, response);
                    }else if (opcion.equals("3")) {
                        RequestDispatcher rd = request.getRequestDispatcher("buildings-servlet?opcion=3");
                        rd.forward(request, response);
                    }else if (opcion.equals("4")) {
                        RequestDispatcher rd = request.getRequestDispatcher("principal_alumno.jsp");
                        rd.forward(request, response);
                    }else if (opcion.equals("5")) {
                        RequestDispatcher rd = request.getRequestDispatcher("select-docent.jsp");
                        rd.forward(request, response);
                    }else if (opcion.equals("6")) {
                        RequestDispatcher rd = request.getRequestDispatcher("eventos-servlet");
                        rd.forward(request, response);
                    }else if (opcion.equals("7")) {
                        RequestDispatcher rd = request.getRequestDispatcher("principal_admin.jsp");
                        rd.forward(request, response);
                    }else if (opcion.equals("8")) {
                        RequestDispatcher rd = request.getRequestDispatcher("principal_docent.jsp");
                        rd.forward(request, response);
                    }else if (opcion.equals("9")) {
                        RequestDispatcher rd = request.getRequestDispatcher("select-alumno.jsp");
                        rd.forward(request, response);
                    }else if (opcion.equals("10")) {
                        RequestDispatcher rd = request.getRequestDispatcher("reservas-servlet");
                        rd.forward(request, response);
                    }else if (opcion.equals("11")) {
                        RequestDispatcher rd = request.getRequestDispatcher("reservas-docent-servlet");
                        rd.forward(request, response);
                    }else if (opcion.equals("12")) {
                        RequestDispatcher rd = request.getRequestDispatcher("principal_pages/reservas.jsp");
                        rd.forward(request, response);
                    }else if (opcion.equals("13")) {
                        RequestDispatcher rd = request.getRequestDispatcher("options-docent-reservations.jsp");
                        rd.forward(request, response);
                    }else if (opcion.equals("14")) {
                        RequestDispatcher rd = request.getRequestDispatcher("view-horarios-servlet?opcion=4");
                        rd.forward(request, response);
                    }
                }else{
                    RequestDispatcher rd = request.getRequestDispatcher("select.jsp");
                    rd.forward(request, response);
                }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

}
