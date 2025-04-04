<%@ page import="com.utez.edu.sira.models.Aulas" %>
<%@ page import="java.util.List" %>
<%@ page import="com.utez.edu.sira.models.Building" %><%--
  Created by IntelliJ IDEA.
  User: kuiss
  Date: 12/08/2024
  Time: 07:56 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Monoton&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="styles/aaa.css">

    <title>GESTOR DE RESERVAS</title>
</head>
<body>
<div class="barra">

    <img src="styles/icons/personaje_1.2.png" alt="" class="logo">
    <h1>SIRA GESTOR DE RESERVAS</h1>

    <div class="close">
        <a href="buildings-servlet?opcion=8">Volver</a>
    </div>
    
</div>

<div class="formulario-registro">

    <form method="post" action="remove-reserva-docent-servlet"  >
    <label class="title_form">Eliminar reserva</label><br><br>
    <input type="number" name="id" placeholder="ID de reserva"><br><br>
    <button type="submit" >Eliminar Reserva</button>
    </form>

</div>

<div class="formulario-registro">

    <form method="post" action="edit-reservas-docent-servlet"  >
        <label class="title_form">Editar reserva</label><br><br>
        <label class="message">Por favor selecciona primero el edificio</label><br><br>
        <select name="building-select" >
            <option value="0">Selecciona un edificio</option>

            <%
                List<Building> lista = (List<Building>) request.getAttribute("buildings");
                String selectedBuildingId = request.getParameter("buildingId");
                if (lista != null) {
                    for (Building b : lista) {
            %>
            <option value="<%= b.getId_edificio()%>" <%= selectedBuildingId != null && selectedBuildingId.equals(String.valueOf(b.getId_edificio())) ? "selected" : "" %> ><%= b.getName()%></option>
            <%
                    }
                }
            %>
        </select>
        <script>
            document.getElementsByName("building-select")[0].onchange = function () {
                let selectValue = this.value;
                window.location.href = "buildings-servlet?opcion=13&buildingId=" +selectValue;

            }
        </script>
        <br><br>
        <select name="room-select" >
            <option value="0">Selecciona un aula</option>
            <%
                List<Aulas> lista2 = (List<Aulas>) request.getAttribute("aulas");
                if (lista2 != null) {
                    for (Aulas a : lista2) {
            %>
            <option value="<%= a.getId_aula()%>"><%= a.getName()%></option>
            <%
                    }
                }
            %>
        </select>
        <br><br>
        <input type="number" name="id" placeholder="ID de la reserva a modificar"><br><br>
        <input type="text" name="titulo" placeholder="Titulo"><br><br>
        <input type="text" name="descripcion" placeholder="Descripcion"><br><br>
        <input type="datetime-local" name="start" placeholder="Fecha de inicio"><br><br>
        <input type="datetime-local" name="end" placeholder="Fecha de finalizacion">


        <br><br>
        <button type="submit" >Editar Reserva</button>
    </form>

</div>
</body>
</html>
