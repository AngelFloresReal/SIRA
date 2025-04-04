
<!---
<%@ page import="com.utez.edu.sira.models.Building" %>
<%@ page import="java.util.List" %>
<%@ page import="com.utez.edu.sira.models.Aulas" %>   <%--
  Created by IntelliJ IDEA.
  User: kuiss
  Date: 27/07/2024
  Time: 02:04 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

-->
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SIRA</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Monoton&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="styles/style_calendar.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min">
    <script src="scripts/calendar-admin.js"></script>
    <script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.15/index.global.min.js'></script>
    <script>



    </script>

</head>

<body>
<div class="barra">
    <h1>SIRA </h1>

    <div class="building-container">
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
                window.location.href = "buildings-docent-servlet?opcion=2&buildingId=" +selectValue;

            }
        </script>
    </div>

    <div class="room-container">
        <select id="aula-select" name="aula-select">
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
        <script>
            document.getElementById("aula-select").onchange = function () {
                let selectValue = parseInt(this.value);
                window.location.href = "buildings-docent-servlet?opcion=1&aulaId=" + selectValue;
            }
        </script>


    </div>

    <div class="logout">
        <a href="logout-servlet" >Cerrar sesion</a>
    </div>

</div>



<nav id="nav-list">
    <ul class="list">
        <li>
            <img src="styles/icons/calendar-check-solid-240.png" alt="" class="nav-img">
        <li><a href="buildings-and-rooms-docent-servlet?opcion=2" id="">
            <span class="nav-item">Gestionar Reservas</span>
        </a></li>

    </ul>
</nav>

<div id='calendar-container' ></div>


<script>

    <%
      String eventosJson = (String) request.getAttribute("eventosJson");
      System.out.println("EVENTOS: " + eventosJson);

      String horariosJson = (String) request.getAttribute("horariosJson");
      System.out.println("HORARIOS: " + horariosJson);
   %>

    var eventosJson = <%= eventosJson %>;
    var horariosJson = <%= horariosJson %>;


    document.addEventListener('DOMContentLoaded', function (){
        const aulaSelect = document.getElementById('aula-select');
        const calendarContainer = document.getElementById('calendar-container');


        mostrarCalendario(calendarContainer);




        function mostrarCalendario(calendarContainer){

            let eventosArray = [];
            eventosArray.push(...horariosJson);
            eventosArray.push(...eventosJson);

            const calendar = new FullCalendar.Calendar(calendarContainer, {
                initialView: 'timeGridWeek',
                events: eventosArray,

                height: 'auto',
                allDaySlot: false,
                buttonText: {
                    today: 'Hoy',
                    month: 'Mes'
                },
                views: {
                    timeGridWeek: {
                        type: 'timeGridWeek',
                        buttonText: 'Semana'
                    },
                    timeGridDay: {
                        type: 'timeGridDay',
                        buttonText: 'Día'
                    },
                },
                headerToolbar: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'dayGridMonth,timeGridWeek,timeGridDay'
                },


            });
            calendar.render();
        }

        function ocultarCalendario(calendarContainer){
            calendarContainer.innerHTML = '';
            const mensaje = document.createElement('h2');
            mensaje.id = 'seleccionador_de_aula';
            mensaje.textContent = 'Selecciona un aula';
            calendarContainer.appendChild(mensaje);
        }

        aulaSelect.addEventListener('change', function(){
            const valor = aulaSelect.value;
            console.log(valor);

            if (valor !== '0'){
                mostrarCalendario(calendarContainer);
                const mensaje = document.getElementById('seleccionador_de_aula');
                mensaje.hidden = true;
            }else {
                mensaje.hidden = false;
            }
        });

        // Ocultar calendario al inicio

    });


</script>


</body>
</html>