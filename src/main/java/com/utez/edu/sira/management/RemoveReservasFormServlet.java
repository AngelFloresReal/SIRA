package com.utez.edu.sira.management;

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

@WebServlet (name = "removereservasformservlet", value = "/remove-reserva-form-servlet")

public class RemoveReservasFormServlet extends HttpServlet {
    Connection connection = MySQLConnection.getConnections();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idParam = request.getParameter("id_reserva");

        if (idParam == null || idParam.trim().isEmpty()) {
            // Mostrar mensaje de error: "El parámetro id es requerido"
            RequestDispatcher rd = request.getRequestDispatcher("alert-remove-reserva-2.jsp");
            rd.forward(request, response);
            return;
        }

        try {
            int id = Integer.parseInt(idParam);
            if (id > 0) {
                PreparedStatement pst = connection.prepareStatement("SELECT * FROM reserva WHERE id_reserva = ?");
                pst.setInt(1, id);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) { // Si la reserva existe
                    pst = connection.prepareStatement("DELETE FROM reserva WHERE id_reserva = ?");
                    pst.setInt(1, id);
                    pst.executeUpdate();

                    RequestDispatcher rd = request.getRequestDispatcher("alert-remove-reserva1.jsp");
                    rd.forward(request, response);
                } else { // Si la reserva no existe
                    RequestDispatcher rd = request.getRequestDispatcher("alert-remove-reserva-2.jsp"); // Mostrar mensaje de error
                    rd.forward(request, response);
                }
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("alert-remove-reserva-2.jsp");
                rd.forward(request, response);
            }
        } catch (NumberFormatException e) {
            // Mostrar mensaje de error: "El parámetro id debe ser un número entero"
            RequestDispatcher rd = request.getRequestDispatcher("alert-remove-reserva-2.jsp");
            rd.forward(request, response);
            return;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }




    }

}
