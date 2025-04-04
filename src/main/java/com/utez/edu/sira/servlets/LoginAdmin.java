package com.utez.edu.sira.servlets;

import com.utez.edu.sira.utils.MySQLConnection;
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
import java.util.UUID;

@WebServlet(name = "loginadmin", value = "/login-admin")
public class LoginAdmin extends HttpServlet {
    Connection connection = MySQLConnection.getConnections();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try {
            String correo = request.getParameter("correo");
            String password = request.getParameter("password");

            if (correo == null || password == null) {
                request.setAttribute("error", "Correo o contraseña inválida");
                request.getRequestDispatcher("admin.jsp").forward(request, response);
                return;
            }

            String consulta = "select id_rol, correo, password, id_usuario from user where correo = ? and password = SHA2(?, 256)";

            PreparedStatement pstmt = connection.prepareStatement(consulta);
            pstmt.setString(1, correo);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int idRol = rs.getInt("id_rol");
                int id_usuario = rs.getInt("id_usuario");
                String email = rs.getString("correo");
                if (idRol == 1 ){

                    HttpSession session = request.getSession();
                    String token = UUID.randomUUID().toString();
                    session.setAttribute("id_usuario", id_usuario);
                    session.setAttribute("token", token);
                    session.setAttribute("correo", email);
                    String tokenSession = (String) session.getAttribute("token");

                    if (tokenSession != null ){
                        response.sendRedirect("buildings-servlet");
                        System.out.println("ID USUARIO: " + id_usuario);
                    }else{
                        response.sendRedirect("index.jsp");
                        System.out.println(tokenSession);

                    }
                }else {
                    System.out.println("Permiso denegado");
                    request.getRequestDispatcher("admin.jsp").forward(request,response);
                }
            } else {
                System.out.println("correo o contraseña incorrecta");
                request.setAttribute("error", "correo o contraseña incorrecto");
                request.getRequestDispatcher("alert-admin.jsp").forward(request, response);
            }
        }catch (Exception e){
            request.getRequestDispatcher("admin.jsp").forward(request,response);
            System.out.println("ALGO FALLO" + e.getMessage());
            e.printStackTrace();
        }

    }



}
