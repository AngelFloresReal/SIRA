package com.utez.edu.sira.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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

@WebServlet(name = "eventosdocentservlet", value = "/eventos-docent-servlet")
public class EventosDocentServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try (Connection connection = MySQLConnection.getConnections()) {



            String aulaId = request.getParameter("aulaId");


            System.out.println("Valor seleccionado: " + aulaId);




                PreparedStatement sentencia3 = connection.prepareStatement("SELECT * FROM reserva WHERE id_aula = ?");
                sentencia3.setInt(1, Integer.parseInt(aulaId));
                ResultSet resultado3 = sentencia3.executeQuery();


                List<Reservas> reservas = new ArrayList<>();

                while (resultado3.next()) {
                    Reservas reserva = new Reservas();
                    reserva.setId_reserva(resultado3.getInt("id_reserva"));
                    reserva.setTittle(resultado3.getString("tittle"));
                    reserva.setDescripcion(resultado3.getString("descripcion"));
                    reserva.setId_usuario(resultado3.getInt("id_usuario"));
                    Timestamp start = resultado3.getTimestamp("start");
                    Timestamp end = resultado3.getTimestamp("end");
                    reserva.setStart(start.toLocalDateTime());
                    reserva.setEnd(end.toLocalDateTime());
                    reserva.setId_edificio(resultado3.getInt("id_edificio"));
                    reserva.setId_aula(resultado3.getInt("id_aula"));
                    reservas.add(reserva);
                }

                System.out.println("SI PASA");


                List<Map<String, Object>> eventos = new ArrayList<>();
                for (Reservas reserva : reservas) {
                    Map<String, Object> evento = new HashMap<>();
                    evento.put("title", reserva.getTittle());
                    evento.put("descripcion", reserva.getDescripcion());
                    LocalDateTime fechaOriginalStart = LocalDateTime.parse(reserva.getStart().toString());
                    LocalDateTime fechaModificadaStart = fechaOriginalStart.minusMonths(1);
                    System.out.println("Fecha Modificada Start: " + fechaModificadaStart);
                    evento.put("start", fechaModificadaStart);
                    LocalDateTime fechaOriginalEnd = LocalDateTime.parse(reserva.getEnd().toString());
                    LocalDateTime fechaModificadaEnd = fechaOriginalEnd.minusMonths(1);
                    System.out.println("Fecha Modificada End: " + fechaModificadaEnd);
                    evento.put("end", fechaModificadaEnd);
                    eventos.add(evento);

                }

                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());
                String eventosJson = mapper.writeValueAsString(eventos);

                request.setAttribute("eventosJson", eventosJson);

                System.out.println(eventos);

                System.out.println("reservas: " + reservas.size());

                request.setAttribute("reservas", reservas);




                RequestDispatcher dispatcher = request.getRequestDispatcher("horarios-servlet?opcion=2");
                dispatcher.forward(request, response);





        }catch (Exception e){
            e.printStackTrace();
            System.out.println("ERROR: " + e.getMessage());
        }



    }




}