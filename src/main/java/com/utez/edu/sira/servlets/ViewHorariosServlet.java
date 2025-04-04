package com.utez.edu.sira.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.utez.edu.sira.models.Horarios;
import com.utez.edu.sira.models.Reservas;
import com.utez.edu.sira.utils.MySQLConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "viewhorariosservlet", value = "/view-horarios-servlet")
public class ViewHorariosServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try (Connection connection = MySQLConnection.getConnections()) {




            PreparedStatement sentencia3 = connection.prepareStatement("SELECT * FROM horarios");
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
                horario.setId_aula(resultado3.getInt("id_aula"));
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
                RequestDispatcher rd = request.getRequestDispatcher("Horarios.jsp");
                rd.forward(request, response);
            }





        }catch (Exception e){
            e.printStackTrace();
            System.out.println("ERROR: " + e.getMessage());
        }



    }




}