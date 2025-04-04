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

@WebServlet (name = "edituserservlet", value = "/edit-user-servlet")
public class EditUserServlet extends HttpServlet {
    Connection connection = MySQLConnection.getConnections();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            HttpSession session = request.getSession();
            Integer id_usuario = (Integer) session.getAttribute("id_usuario");

            System.out.println(id_usuario);

            LocalDateTime ahora = LocalDateTime.now();


            String idStr = request.getParameter("id");
            String name = request.getParameter("f_name");
            String l_name = request.getParameter("l_name");
            String email = request.getParameter("correo");
            String matricula = request.getParameter("matricula");
            String password = request.getParameter("password");
            int rol = Integer.parseInt(request.getParameter("id_rol"));


            if (idStr == null ||name.isEmpty() || l_name.isEmpty() || email.isEmpty() || password.isEmpty() || rol == 0) {
                System.out.println("vacio");
                request.getRequestDispatcher("alert-register.jsp").forward(request, response);
            } else {


                        int id = Integer.parseInt(idStr);
                        // Insert the new reservation
                        PreparedStatement ps = connection.prepareStatement("update user set first_name = ?, last_name = ?, correo = ?, matricula = ?, password = SHA2(?,256), id_rol = ? where id_usuario = " + idStr);
                        ps.setString(1, name);
                        ps.setString(2, l_name);
                        ps.setString(3,email);
                        ps.setString(4,matricula);
                        ps.setString(5,password);
                        ps.setInt(6,rol);
                        ps.executeUpdate();

                        RequestDispatcher rd = request.getRequestDispatcher("alert-edit-user-1.jsp");
                        rd.forward(request, response);
                    }





        }catch (Exception e) {
            System.out.println("ERROR" + e.getMessage());
        }


    }

}
