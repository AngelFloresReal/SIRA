package com.utez.edu.sira.servlets;

import com.utez.edu.sira.management.EmailSender;
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

@WebServlet (name = "addhorarioservlet", value = "/add-horario-servlet")
public class AddHorarioServlet extends HttpServlet {
    Connection connection = MySQLConnection.getConnections();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {



            LocalDateTime ahora = LocalDateTime.now();

            String tittle = request.getParameter("tittle");
            int edificio = Integer.parseInt(request.getParameter("building-select"));
            int room = Integer.parseInt(request.getParameter("room-select"));

            String startStr = request.getParameter("start");
            String endStr = request.getParameter("end");

            int repeatMonths = Integer.parseInt(request.getParameter("repeat-months"));


            if (tittle.isEmpty()  || startStr.isEmpty() || endStr.isEmpty() || edificio == 0 || room == 0 || repeatMonths == 0) {
                System.out.println("vacio");
                request.getRequestDispatcher("alert-horario.jsp").forward(request, response);
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                LocalDateTime start = LocalDateTime.parse(startStr, formatter);
                LocalDateTime end = LocalDateTime.parse(endStr, formatter);


                if ( start.getHour() < 7 || start.getHour() > 21 ||  start.getDayOfWeek().getValue() == 7 || end.getHour() < 7 || end.getHour() > 21  || end.getDayOfWeek().getValue() == 7){
                    System.out.println("no se puede reservar en esa fecha");
                    request.getRequestDispatcher("alert-horario.jsp").forward(request, response);
                } else {
                    // Check if the room is already reserved for the same time range
                    PreparedStatement psCheck = connection.prepareStatement("SELECT * FROM horarios WHERE id_aula = ? AND start < ? AND end > ? ");
                    psCheck.setInt(1, room);
                    psCheck.setTimestamp(2, Timestamp.valueOf(end));
                    psCheck.setTimestamp(3, Timestamp.valueOf(start));

                    ResultSet rs = psCheck.executeQuery();

                    if (rs.next()) {
                        System.out.println("La sala ya está reservada para ese rango de fecha");
                        request.getRequestDispatcher("alert-horario.jsp").forward(request, response);
                    } else {

                        for (int i = 0; i < repeatMonths; i++) {
                            LocalDateTime newStart = start.plusWeeks(i);
                            LocalDateTime newEnd = end.plusWeeks(i);

                            // Insert the new reservation
                            PreparedStatement ps = connection.prepareStatement("INSERT INTO horarios (tittle,start,end,id_edificio,id_aula) VALUES (?,?,?,?,?)");
                            ps.setString(1, tittle);
                            ps.setTimestamp(2, Timestamp.valueOf(newStart));
                            ps.setTimestamp(3, Timestamp.valueOf(newEnd));
                            ps.setInt(4, edificio);
                            ps.setInt(5, room);
                            ps.executeUpdate();
                        }


                        RequestDispatcher rd = request.getRequestDispatcher("alert-horario2.jsp");
                        rd.forward(request, response);
                    }
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }


    }


}
