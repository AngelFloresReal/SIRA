package com.utez.edu.sira.servlets;

import com.utez.edu.sira.utils.MySQLConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;


@WebServlet(name = "loginservlet", value = "/login-servlet")
public class LoginServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = MySQLConnection.getConnections();

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);


        try {
            String matricula = request.getParameter("matricula");
            String password = request.getParameter("password");

            if (matricula == null || password == null || matricula.equals("") || password.equals("")) {
                request.setAttribute("error", "Matrícula o contraseña inválida");
                request.getRequestDispatcher("alert.jsp").forward(request, response);
                request.getRequestDispatcher("alert.jsp").forward(request, response);
                return;
            }

            String consulta = "select id_rol, matricula, password, id_usuario from user where matricula = ? and password = SHA2(?, 256)";

            PreparedStatement pstmt = connection.prepareStatement(consulta);
            pstmt.setString(1, matricula);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int idRol = rs.getInt("id_rol");
                int id_usuario = rs.getInt("id_usuario");
                if (idRol == 3 || idRol == 2 || idRol == 1 ){

                    HttpSession session = request.getSession();
                    String token = UUID.randomUUID().toString();
                    session.setAttribute("id_usuario", id_usuario);
                    session.setAttribute("token", token);
                    String tokenSession = (String) session.getAttribute("token");

                    if (tokenSession != null ){
                        response.sendRedirect("buildings-servlet?opcion=9");
                        System.out.println("ID USUARIO: " + id_usuario);
                    }else{
                        response.sendRedirect("index.jsp");
                        System.out.println(tokenSession);

                    }

                }else {
                    System.out.println("Permiso denegado");
                }
            } else {
                System.out.println("matricula o contraseña incorrecta");
                request.setAttribute("error", "matricula o contraseña incorrecto");
                request.getRequestDispatcher("alert.jsp").forward(request, response);


            }
            connection.close();
        }catch (Exception e){
            System.out.println("ALGO FALLO" + e.getMessage());
            e.printStackTrace();
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

    }

}
