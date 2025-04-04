package com.utez.edu.sira.servlets;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.utez.edu.sira.HelloServlet;
import com.utez.edu.sira.models.Reservas;
import com.utez.edu.sira.utils.MySQLConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet (name = "reservasservlet", value = "/reservas-servlet")
public class ReservasServlet extends HelloServlet {
    Connection connection = MySQLConnection.getConnections();


        @Override
        public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            try {

                Statement sentencia = connection.createStatement();
                ResultSet resultado = sentencia.executeQuery("SELECT r.*, e.name as edificio_name, a.name as aula_name, u.first_name as user_name FROM reserva r join edificios e on r.id_edificio = e.id_edificio join aula a on r.id_aula = a.id_aula join user u on r.id_usuario = u.id_usuario");

                List<Reservas> reservas = new ArrayList<>();

                while (resultado.next()){
                    Reservas reserva = new Reservas();
                    reserva.setId_reserva(resultado.getInt("id_reserva"));
                    reserva.setTittle(resultado.getString("tittle"));
                    reserva.setDescripcion(resultado.getString("descripcion"));
                    reserva.setId_usuario(resultado.getInt("id_usuario"));
                    reserva.setUsuario_name(resultado.getString("user_name"));
                    Timestamp start = resultado.getTimestamp("start");
                    Timestamp end = resultado.getTimestamp("end");
                    reserva.setStart(start.toLocalDateTime());
                    reserva.setEnd(end.toLocalDateTime());
                    reserva.setId_edificio(resultado.getInt("id_edificio"));
                    reserva.setEdificio_name(resultado.getString("edificio_name"));
                    reserva.setId_aula(resultado.getInt("id_aula"));
                    reserva.setAula_name(resultado.getString("aula_name"));
                    reservas.add(reserva);
                }

                List<Map<String, Object>> eventos = new ArrayList<>();
                for (Reservas reserva : reservas) {
                    Map<String, Object> evento = new HashMap<>();
                    evento.put("title", reserva.getTittle());
                    evento.put("descripcion", reserva.getDescripcion());
                    evento.put("start", reserva.getStart());
                    evento.put("end", reserva.getEnd());
                    eventos.add(evento);

                }

                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());
                String eventosJson = mapper.writeValueAsString(eventos);

                request.setAttribute("eventosJson", eventosJson);

                System.out.println(eventos);

                System.out.println("reservas: " + reservas.size());

                request.setAttribute("reservas", reservas);


                    RequestDispatcher dispatcher = request.getRequestDispatcher("principal_pages/reservas.jsp");
                    dispatcher.forward(request, response);




            }catch (SQLException e){
                e.printStackTrace();
            }


        }




}
