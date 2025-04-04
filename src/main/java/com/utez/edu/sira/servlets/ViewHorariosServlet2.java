package com.utez.edu.sira.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.utez.edu.sira.models.Aulas;
import com.utez.edu.sira.models.Building;
import com.utez.edu.sira.models.Horarios;
import com.utez.edu.sira.utils.MySQLConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "viewhorariosservlet2", value = "/view-horarios-servlet2")
public class ViewHorariosServlet2 extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try (Connection connection = MySQLConnection.getConnections()) {


            int pagina = 1; // variable para almacenar la página actual
            int registrosPorPagina = 8;


            PreparedStatement sentencia3 = connection.prepareStatement("SELECT h.*, e.name as edificio_name, a.name as aula_name FROM horarios h join edificios e on h.id_edificio = e.id_edificio join aula a on h.id_aula = a.id_aula LIMIT ? OFFSET ?");
            sentencia3.setInt(1, registrosPorPagina); // establece el número de registros por página
            sentencia3.setInt(2, (pagina - 1) * registrosPorPagina);
            ResultSet resultado3 = sentencia3.executeQuery();


            List<Horarios> horarios = new ArrayList<>();

            while (resultado3.next()) {
                Horarios horario = new Horarios();
                horario.setId_horario(resultado3.getInt("id_horario"));
                horario.setTittle(resultado3.getString("tittle"));
                Timestamp start = resultado3.getTimestamp("start");
                Timestamp end = resultado3.getTimestamp("end");
                horario.setStart(start.toLocalDateTime());
                horario.setEnd(end.toLocalDateTime());
                horario.setId_edificio(resultado3.getInt("id_edificio"));
                horario.setEdificio_name(resultado3.getString("edificio_name"));
                horario.setId_aula(resultado3.getInt("id_aula"));
                horario.setAula_name(resultado3.getString("aula_name"));
                horarios.add(horario);
            }

            System.out.println("SI PASA");


            List<Map<String, Object>> eventos2 = new ArrayList<>();
            for (Horarios horario : horarios) {
                Map<String, Object> evento = new HashMap<>();
                evento.put("title", horario.getTittle());
                LocalDateTime fechaOriginalStart = LocalDateTime.parse(horario.getStart().toString());
                LocalDateTime fechaModificadaStart = fechaOriginalStart.minusMonths(1);
                System.out.println("Fecha Modificada Start: " + fechaModificadaStart);
                evento.put("start", fechaModificadaStart);
                LocalDateTime fechaOriginalEnd = LocalDateTime.parse(horario.getEnd().toString());
                LocalDateTime fechaModificadaEnd = fechaOriginalEnd.minusMonths(1);
                System.out.println("Fecha Modificada End: " + fechaModificadaEnd);
                evento.put("end", fechaModificadaEnd);

                eventos2.add(evento);

            }

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            String horariosJson = mapper.writeValueAsString(eventos2);

            request.setAttribute("horariosJson", horariosJson);

            System.out.println(eventos2);

            System.out.println("reservas: " + horarios.size());

            request.setAttribute("reservas", horarios);


            Statement sentencia = connection.createStatement();
            ResultSet resultado = sentencia.executeQuery("select e.*, e.name as edificio_name from edificios e");


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



            String opcion = request.getParameter("opcion");
            if (opcion != null) {
                if (opcion.equals("1")) {
                    RequestDispatcher rd = request.getRequestDispatcher("principal_admin.jsp");
                    rd.forward(request, response);
                } else if (opcion.equals("2")) {
                    RequestDispatcher rd = request.getRequestDispatcher("principal_docent.jsp");
                    rd.forward(request, response);
                }else if (opcion.equals("3")) {
                    RequestDispatcher rd = request.getRequestDispatcher("principal_alumno.jsp");
                    rd.forward(request, response);
                }else if (opcion.equals("4")) {
                    RequestDispatcher rd = request.getRequestDispatcher("principal_pages/Horarios.jsp");
                    rd.forward(request, response);
                }
            }else{
                RequestDispatcher rd = request.getRequestDispatcher("principal_pages/Horarios.jsp");
                rd.forward(request, response);
            }





        }catch (Exception e){
            e.printStackTrace();
            System.out.println("ERROR: " + e.getMessage());
        }



    }




}