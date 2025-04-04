package com.utez.edu.sira.servlets;

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


@WebServlet (name = "registerservlet", value = "/register-servlet")
public class RegiserServlet extends HttpServlet {
    Connection connection = MySQLConnection.getConnections();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String nombre = req.getParameter("nombre");
            String apellido = req.getParameter("apellido");
            String correo = req.getParameter("correo");
            String matricula = req.getParameter("matricula");
            String password = req.getParameter("password");

            int rol = Integer.parseInt(req.getParameter("rol-select"));

            if (nombre == null || apellido == null || correo == null || password == null || rol == 0 || correo.isEmpty() ) {
                System.out.println("vacio");
                req.getRequestDispatcher("alert-register.jsp").forward(req, resp);
            }else {

                PreparedStatement psVerificar = connection.prepareStatement("SELECT * FROM user WHERE correo = ? OR matricula = ?");
                psVerificar.setString(1, correo);
                psVerificar.setString(2, matricula);
                ResultSet rs = psVerificar.executeQuery();

                if (rs.next()) {
                    req.setAttribute("error", "El correo o matricula ya existen en la base de datos");
                    req.getRequestDispatcher("alert-register2.jsp").forward(req, resp);
                    System.out.println("nononoonon");
                }else{
                    PreparedStatement ps = connection.prepareStatement("INSERT INTO user (first_name,last_name,correo,matricula,password,id_rol) VALUES (?,?,?,?,SHA2(?,256),?)");
                    ps.setString(1,nombre);
                    ps.setString(2,apellido);
                    ps.setString(3,correo);
                    ps.setString(4,matricula);
                    ps.setString(5,password);
                    ps.setInt(6,rol);
                    ps.executeUpdate();

                    RequestDispatcher rd = req.getRequestDispatcher("alert-user.jsp");
                    rd.forward(req, resp);

                }
            }




        }catch (Exception e){
            e.printStackTrace();

            RequestDispatcher rd = req.getRequestDispatcher("alert-user.jsp");
            rd.forward(req, resp);

        }




    }

}
