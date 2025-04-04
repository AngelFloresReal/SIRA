<%@ page import="com.utez.edu.sirav5.models.UsuarioBean" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reserva Lista</title>
</head>
<body>
<h1>Lista de reservas</h1>
<br/>
<a href = "views/ReservaAgregar.jsp"> Agregar Reserva </a>
<table border = 1px>
    <tr>
        <th>ID</th>
        <th>Nombre de la reserva</th>
        <th>Descripcion</th>
        <th>Usuario</th>
        <th>Inicio</th>
        <th>Fin</th>
        <th>Editar</th>
        <th>Eliminar</th>
    </tr>
    <%
        List<UsuarioBean> listaReservas = (List<UsuarioBean>)request.getAttribute("listaReservas");
        if(listaReservas != null){
            for(UsuarioBean reserva : listaReservas){
    %>
    <tr>
        <td><%= reserva.getIdReserva()%></td>
        <td><%= reserva.getNombre()%></td>
        <td><%= reserva.getDescripcion()%></td>
        <td><%= reserva.getUsuario()%></td>
        <td><%= reserva.getHorarioInicio()%></td>
        <td><%= reserva.getHorarioFin()%></td>
        <td> <a href = "reserva-data-actualizar?idReserva=<%= reserva.getIdReserva()%>">Editar</a>  </td>
        <td> <a href = "reserva-eliminar?idReserva=<%= reserva.getIdReserva()%>">Eliminar</a>  </td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>
