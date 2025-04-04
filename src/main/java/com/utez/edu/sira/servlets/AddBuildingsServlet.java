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

@WebServlet (name = "addbuildingsservlet", value = "/add-buildings-servlet")
public class AddBuildingsServlet extends HttpServlet {
    Connection connection = MySQLConnection.getConnections();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {


        try {

            String name =req.getParameter("building");

            if ( name.isEmpty()){



                RequestDispatcher rd = req.getRequestDispatcher("alert-edificio1.jsp");
                rd.forward(req, resp);

            }else if(!name.isEmpty()){

                PreparedStatement psVerificar = connection.prepareStatement("SELECT * FROM edificios WHERE name = ?");
                psVerificar.setString(1, name);
                ResultSet rs = psVerificar.executeQuery();

                if (rs.next()){

                    req.setAttribute("error", "El id o nombre ya existen en la base de datos");
                    req.getRequestDispatcher("alert-edificio3.jsp").forward(req, resp);
                    System.out.println("nononoonon");

                }else {
                    PreparedStatement ps = connection.prepareStatement("insert into edificios (name) values (?)");

                    ps.setString(1, name);
                    ps.executeUpdate();

                    RequestDispatcher rd = req.getRequestDispatcher("alert-edificios2.jsp");
                    rd.forward(req, resp);

                }




            }

        }catch (Exception e){
            System.out.println("ERROR" + e.getMessage());
            }

        }

    }





