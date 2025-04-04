<%@ page import="com.utez.edu.sira.models.Usuario" %>
<%@ page import="java.util.List" %>
<%@ page import="com.utez.edu.sira.models.Building" %>
<%@ page import="com.utez.edu.sira.models.Aulas" %><%--
  Created by IntelliJ IDEA.
  User: kuiss
  Date: 31/07/2024
  Time: 09:48 p. m.
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
    <title>SIRA GESTOR DE EDIFICIOS Y AULAS</title>
</head>
<body>


<div class="barra">

    <h1>SIRA </h1>

    <div class="building-container">
        <select name="building-select" >
            <option value="0">Selecciona un edificio</option>

            <%
                List<Building> lista5 = (List<Building>) request.getAttribute("buildings");
                String selectedBuildingId3 = request.getParameter("buildingId");
                if (lista5 != null) {
                    for (Building b : lista5) {
            %>
            <option value="<%= b.getId_edificio()%>" <%= selectedBuildingId3 != null && selectedBuildingId3.equals(String.valueOf(b.getId_edificio())) ? "selected" : "" %> ><%= b.getName()%></option>
            <%
                    }
                }
            %>
        </select>
        <script>
            document.getElementsByName("building-select")[0].onchange = function () {
                let selectValue = this.value;
                window.location.href = "buildings-servlet?opcion=7&buildingId=" +selectValue;

            }
        </script>
    </div>

    <div class="room-container">
        <select id="aula-select" name="aula-select">
            <option value="0">Selecciona un aula</option>
            <%
                List<Aulas> lista6 = (List<Aulas>) request.getAttribute("aulas");
                if (lista6 != null) {
                    for (Aulas a : lista6) {
            %>
            <option value="<%= a.getId_aula()%>"><%= a.getName()%></option>
            <%
                    }
                }
            %>
        </select>
        <script>
            document.getElementById("aula-select").onchange = function () {
                let selectValue = parseInt(this.value);
                window.location.href = "buildings-servlet?opcion=6&aulaId=" + selectValue;

            }
        </script>


    </div>

    <div class="close">
        <a href="logout-servlet" >Cerrar sesion</a>
    </div>




</div>


<nav id="nav-list">
    <ul class="list_nav">
        <li>
            <img src="styles/icons/calendar-check-solid-240.png" alt="" class="nav-img">
        <li><a href="buildings-and-rooms-servlet?opcion=2" >
            <span class="nav-item">Gestionar Reservas</span>
        </a></li>
        <img src="styles/icons/calendar-regular-240.png" alt="" class="nav-img">
        <a href="view-horarios-servlet2?opcion=4">
            <span class="nav-item">Gestionar horarios</span>
        </a>
        </li>
        <li>
            <img src="styles/icons/school-solid-240.png" alt="" class="nav-img">
            <a href="buildings-and-rooms-servlet?opcion=1">
                <span class="nav-item">Gestionar edificios y aulas</span>
            </a></li>
        <li>
            <img src="styles/icons/user-account-solid-240.png" alt="" class="nav-img">
            <a href="user-servlet">
                <span class="nav-item">Gestionar usuarios</span>
            </a></li>

    </ul>
</nav>



<div class="formulario-registro ">
    <form method="post" action="add-buildings-servlet" >
        <label class="title_form">Edificios</label><br><br>
        <input type="text" name="building" placeholder="Nombre del edificio"><br><br>
        <button type="submit" >Agregar</button>
    </form>


</div>

<br><br>

<div class="formulario-registro form_2">
    <form method="post" action="add-room-servlet" >
        <label class="title_form">Agregar aula</label><br><br>
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
                window.location.href = "buildings-servlet?opcion=1&buildingId=" +selectValue;

            }
        </script>
        <br><br>
        <input type="text" name="room" placeholder="Nombre del aula"><br><br>

        <button type="submit" >Agregar</button>
    </form>
</div>



<div class="list list_1 user">

    <table border = 1px>
        <thead class="title_table">
        <tr>
            <th>Nombre</th>
            <th>Eliminar</th>

        </tr>
        </thead>

        <%
            List<Building> lista2 = (List<Building>)request.getAttribute("buildings");
            if(lista2 != null){
                for(Building building : lista2){
        %>
        <tr>
            <td><%= building.getName()%></td>
            <td>
                <a href="remove-building-servlet?id_building=<%= building.getId_edificio()%>">
                    <img src="styles/icons/trash-regular-240.png" alt="Eliminar reserva" width="30" height="30">
                </a>
            </td>


        </tr>
        <%
                }
            }
        %>
    </table>

</div>

<div class="list list_2 user ">

    <table border = 1px>
        <thead class="title_table">
        <tr>
            <th>Nombre</th>
            <th>Edificio</th>
            <th>Eliminar</th>

        </tr>
        </thead>

        <%
            List<Aulas> lista3 = (List<Aulas>)request.getAttribute("aulas");
            if(lista3 != null){
                for(Aulas aulas : lista3){
        %>
        <tr>
            <td><%= aulas.getName()%></td>
            <td><%= aulas.getEdificio_name()%></td>
            <td>
                <a href="remove-room-servlet?id_room=<%= aulas.getId_aula()%>">
                    <img src="styles/icons/trash-regular-240.png" alt="Eliminar reserva" width="30" height="30">
                </a>
            </td>


        </tr>
        <%
                }
            }
        %>
    </table>

</div>

</body>
</html>
