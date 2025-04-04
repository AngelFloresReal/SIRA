<%@ page import="com.utez.edu.sira.models.Usuario" %>
<%@ page import="java.util.List" %>
<%@ page import="com.utez.edu.sira.models.Reservas" %>
<%@ page import="com.utez.edu.sira.models.Building" %>
<%@ page import="com.utez.edu.sira.models.Horarios" %>
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
    <title>SIRA HORARIOS</title>
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



<div class="formulario-registro">
    <form method="post" action="add-horario-servlet">
        <label for="" class="title_form">Registar horario</label><br><br>
        <label for="" class="message">Por favor selecciona primero el edificio</label><br><br>
        <select name="building-select" id="building-select">
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
            document.getElementById("building-select").onchange = function () {
                let selectValue = this.value;
                fetch("buildings-servlet?opcion=15&buildingId=" + selectValue)
                    .then(response => response.json())
                    .then(data => {
                        let roomSelect = document.getElementById("rooms-select");
                        roomSelect.innerHTML = "";
                        data.forEach(aula => {
                            let option2 = document.createElement("option");
                            option2.value = aula.id_aula;
                            option2.text = aula.name;
                            roomSelect.appendChild(option2);
                        });
                    })
                    .catch(error => console.error("Error al obtener aulas:", error));
            };
        </script>
        <br><br>
        <select name="room-select" id="rooms-select">
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
        <input type="text" name="tittle" placeholder="Titulo"><br><br>
        <label  class="message">Ingresa la fecha de inicio</label><br><br>
        <input type="datetime-local" name="start" placeholder="Fecha de inicio"><br><br>
        <label  class="message">Ingresa la fecha de finalizacion</label><br><br>
        <input type="datetime-local" name="end" placeholder="Fecha de finalizacion"><br><br>
        <label class="message">Número de Semanas a repetir el horario</label><br><br>
        <input type="number" name="repeat-months" placeholder="Num Semanas"><br><br>

        <button type="submit" id="">Reservar</button>
    </form>
</div>

<div class="list">

    <table>

        <thead  class="title_table">
        <tr>
            <th>ID</th>
            <th>Titulo</th>
            <th>Inicio</th>
            <th>Fin</th>
            <th>Edificio</th>
            <th>Aula</th>
        </tr>
        </thead>

        <%
            List<Horarios> horarios = (List<Horarios>)request.getAttribute("reservas");
            if(horarios != null){
                for(Horarios horario : horarios){
        %>
        <tr>
            <td><%= horario.getId_horario()%></td>
            <td><%= horario.getTittle()%></td>
            <td><%= horario.getStart()%></td>
            <td><%= horario.getEnd()%></td>
            <th><%= horario.getEdificio_name()%></th>
            <th><%= horario.getAula_name()%></th>

        </tr>
        <%
                }
            }
        %>
    </table>

</div>

</body>
</html>
