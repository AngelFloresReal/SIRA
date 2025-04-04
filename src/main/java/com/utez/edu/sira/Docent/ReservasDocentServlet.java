package com.utez.edu.sira.Docent;

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
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet (name = "reservasdocentservlet", value = "/reservas-docent-servlet")
public class ReservasDocentServlet extends HttpServlet {

    Connection connection = MySQLConnection.getConnections();


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            HttpSession session = request.getSession();
            Integer id_usuario = (Integer) session.getAttribute("id_usuario");

            Statement sentencia = connection.createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM reserva where id_usuario = " + id_usuario);

            List<Reservas> reservas = new ArrayList<>();

            while (resultado.next()){
                Reservas reserva = new Reservas();
                reserva.setId_reserva(resultado.getInt("id_reserva"));
                reserva.setTittle(resultado.getString("tittle"));
                reserva.setDescripcion(resultado.getString("descripcion"));
                reserva.setId_usuario(resultado.getInt("id_usuario"));
                Timestamp start = resultado.getTimestamp("start");
                Timestamp end = resultado.getTimestamp("end");
                reserva.setStart(start.toLocalDateTime());
                reserva.setEnd(end.toLocalDateTime());
                reserva.setId_edificio(resultado.getInt("id_edificio"));
                reserva.setId_aula(resultado.getInt("id_aula"));
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

            RequestDispatcher dispatcher = request.getRequestDispatcher("principal_pages/reservas-docent.jsp");
            dispatcher.forward(request, response);


        }catch (SQLException e){
            e.printStackTrace();
        }


    }




}
