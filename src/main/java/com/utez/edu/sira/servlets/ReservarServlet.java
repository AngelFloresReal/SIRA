package com.utez.edu.sira.servlets;

import com.utez.edu.sira.HelloServlet;
import com.utez.edu.sira.management.EmailSender;
import com.utez.edu.sira.utils.MySQLConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet (name = "reservarservlet", value = "/reservar-servlet")
public class ReservarServlet extends HelloServlet {
    Connection connection = MySQLConnection.getConnections();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        try {

            HttpSession session = req.getSession();
            Integer id_usuario = (Integer) session.getAttribute("id_usuario");
            String email = (String) session.getAttribute("correo");

            LocalDateTime ahora = LocalDateTime.now();

            String tittle = req.getParameter("tittle");
            String descripcion = req.getParameter("descripcion");
            int edificio = Integer.parseInt(req.getParameter("building-select"));
            int room = Integer.parseInt(req.getParameter("room-select"));

            String startStr = req.getParameter("start");
            String endStr = req.getParameter("end");


            if (tittle.isEmpty() || descripcion.isEmpty() || startStr.isEmpty() || endStr.isEmpty() || edificio == 0 || room == 0) {
                System.out.println("vacio");
                req.getRequestDispatcher("alert-reserva.jsp").forward(req, resp);
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                LocalDateTime start = LocalDateTime.parse(startStr, formatter);
                LocalDateTime end = LocalDateTime.parse(endStr, formatter);

                if (start.isBefore(ahora) || start.getHour() < 7 || start.getHour() > 21 ||  start.getDayOfWeek().getValue() == 7 || end.getHour() < 7 || end.getHour() > 21  || end.getDayOfWeek().getValue() == 7){
                    System.out.println("no se puede reservar en esa fecha");
                    req.getRequestDispatcher("alert-reserva3.jsp").forward(req, resp);
                } else {
                    // Check if the room is already reserved for the same time range
                    PreparedStatement psCheck = connection.prepareStatement("SELECT * FROM reserva WHERE id_aula = ? AND start < ? AND end > ?");
                    psCheck.setInt(1, room);
                    psCheck.setTimestamp(2, Timestamp.valueOf(end));
                    psCheck.setTimestamp(3, Timestamp.valueOf(start));

                    ResultSet rs = psCheck.executeQuery();

                    if (rs.next()) {
                        System.out.println("La sala ya está reservada para ese rango de fecha");
                        req.getRequestDispatcher("alert-reserva4.jsp").forward(req, resp);
                    } else {
                        // Insert the new reservation
                        PreparedStatement ps = connection.prepareStatement("INSERT INTO reserva (tittle,descripcion,id_usuario,start,end,id_edificio,id_aula) VALUES (?,?,?,?,?,?,?)");
                        ps.setString(1, tittle);
                        ps.setString(2, descripcion);
                        ps.setInt(3, id_usuario);
                        ps.setTimestamp(4, Timestamp.valueOf(start));
                        ps.setTimestamp(5, Timestamp.valueOf(end));
                        ps.setInt(6, edificio);
                        ps.setInt(7, room);
                        ps.executeUpdate();

                        try {
                            EmailSender.sendEmail(email, tittle, descripcion, startStr, endStr, edificio, room);
                        } catch (Exception e) {
                            System.out.println("Error al enviar correo electrónico: " + e.getMessage());
                        }

                        RequestDispatcher rd = req.getRequestDispatcher("alert-reserva2.jsp");
                        rd.forward(req, resp);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("no se pudo  " + e.toString());
        }
    }
}
