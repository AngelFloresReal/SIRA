package com.utez.edu.sira.management;

import com.utez.edu.sira.utils.MySQLConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet (name = "editreservasdocentservlet", value = "/edit-reservas-docent-servlet")
public class EditReservasDocentServlet extends HttpServlet {
    Connection connection = MySQLConnection.getConnections();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            HttpSession session = request.getSession();
            Integer id_usuario = (Integer) session.getAttribute("id_usuario");

            System.out.println(id_usuario);

            LocalDateTime ahora = LocalDateTime.now();


            String idStr = request.getParameter("id");
            String titulo = request.getParameter("titulo");
            String descripcion = request.getParameter("descripcion");
            int edificio = Integer.parseInt(request.getParameter("building-select2"));
            int room = Integer.parseInt(request.getParameter("room-select"));
            String startStr = request.getParameter("start");
            String endStr = request.getParameter("end");


            if (idStr == null || titulo.isEmpty() || descripcion.isEmpty() || startStr.isEmpty() || endStr.isEmpty() || edificio == 0 || room == 0) {
                System.out.println("vacio");
                request.getRequestDispatcher("alert-reserva5.jsp").forward(request, response);
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                LocalDateTime start = LocalDateTime.parse(startStr, formatter);
                LocalDateTime end = LocalDateTime.parse(endStr, formatter);

                if (start.isBefore(ahora) || start.getHour() < 7 || start.getHour() > 21 || start.getDayOfWeek().getValue() ==6 || start.getDayOfWeek().getValue() == 7 || end.getHour() < 7 || end.getHour() > 21 || end.getDayOfWeek().getValue() == 6 || end.getDayOfWeek().getValue() == 7){
                    System.out.println("no se puede reservar en esa fecha");
                    request.getRequestDispatcher("alert-reserva6.jsp").forward(request, response);
                } else {
                    // Check if the room is already reserved for the same time range
                    PreparedStatement psCheck = connection.prepareStatement("SELECT * FROM reserva WHERE id_aula = ? AND start <= ? AND end >= ?");
                    psCheck.setInt(1, room);
                    psCheck.setTimestamp(2, Timestamp.valueOf(end));
                    psCheck.setTimestamp(3, Timestamp.valueOf(start));

                    ResultSet rs = psCheck.executeQuery();

                    if (rs.next()) {
                        System.out.println("La sala ya está reservada para ese rango de fecha");
                        request.getRequestDispatcher("alert-reserva7.jsp").forward(request, response);
                    } else {

                        int id = Integer.parseInt(idStr);
                        // Insert the new reservation
                        PreparedStatement ps = connection.prepareStatement("update reserva set tittle = ?, descripcion = ?, start = ?, end = ?,id_edificio = ?, id_aula = ? where id_reserva = ? and id_usuario = ?");
                        ps.setString(1, titulo);
                        ps.setString(2, descripcion);
                        ps.setTimestamp(3, Timestamp.valueOf(start));
                        ps.setTimestamp(4, Timestamp.valueOf(end));
                        ps.setInt(5, edificio);
                        ps.setInt(6, room);
                        ps.setInt(7, id);
                        ps.setInt(8, id_usuario);
                        ps.executeUpdate();

                        RequestDispatcher rd = request.getRequestDispatcher("alert-edit-reserva2.jsp");
                        rd.forward(request, response);
                    }
                }
            }



        }catch (Exception e) {
            System.out.println("ERROR" + e.getMessage());
        }


    }

}
