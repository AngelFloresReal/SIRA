<%@ page import="com.utez.edu.sira.models.Usuario" %>
<%@ page import="java.util.List" %>
<%@ page import="com.utez.edu.sira.models.Reservas" %>
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
    <title>SIRA RESERVAS</title>
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
    <form method="post" action="reservar-servlet">
        <label for="" class="title_form">Reservar</label><br><br>
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
                        let roomSelect = document.getElementById("room-select");
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
        <select name="room-select" id="room-select" >
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
        <input type="text" name="descripcion" placeholder="Descripcion"><br><br>
        <input type="datetime-local" name="start" placeholder="Fecha de inicio"><br><br>
        <input type="datetime-local" name="end" placeholder="Fecha de finalizacion"><br><br>


        <button type="submit" id="">Reservar</button>
    </form>
</div>

<div class="list">

    <table>

        <thead  class="title_table">
        <tr>
            <th>Titulo</th>
            <th>Descripcion</th>
            <th>Usuario</th>
            <th>Start</th>
            <th>End</th>
            <th>Edificio</th>
            <th>Aula</th>
            <th>Editar</th>
            <th>Eliminar</th>
        </tr>
        </thead>

        <%
            List<Reservas> reservas = (List<Reservas>)request.getAttribute("reservas");
            if(reservas != null){
                for(Reservas reserva : reservas){
        %>
        <tr>
            <td><%= reserva.getTittle()%></td>
            <td><%= reserva.getDescripcion()%></td>
            <td><%= reserva.getUsuario_name()%></td>
            <td><%= reserva.getStart()%></td>
            <td><%= reserva.getEnd()%></td>
            <th><%= reserva.getEdificio_name()%></th>
            <th><%= reserva.getAula_name()%></th>
            <td>
                <a class="abrir-modal" data-reserva-id="<%= reserva.getId_reserva()%>">
                    <img src="styles/icons/edit-regular-240.png" alt="Editar reserva" width="30" height="30">
                </a>
            </td>
            <td>
                <a href="remove-reserva-form-servlet?id_reserva=<%= reserva.getId_reserva()%>">
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


<dialog id="modal" class="formulario-registro form_3">

    <form method="post" action="edit-reservas-servlet"  >
        <label class="title_form">Editar reserva</label><br><br>
        <label class="message">Por favor selecciona primero el edificio</label><br><br>
        <select name="building-select2" id="building-select2">
            <option value="0">Selecciona un edificio</option>

            <%
                List<Building> lista3 = (List<Building>) request.getAttribute("buildings");
                String selectedBuildingId2 = request.getParameter("buildingId");
                if (lista3 != null) {
                    for (Building b : lista3) {
            %>
            <option value="<%= b.getId_edificio()%>" <%= selectedBuildingId2 != null && selectedBuildingId2.equals(String.valueOf(b.getId_edificio())) ? "selected" : "" %> ><%= b.getName()%></option>
            <%
                    }
                }
            %>
        </select>
        <script>
            document.getElementById("building-select2").onchange = function () {
                let selectValue = this.value;
                fetch("buildings-servlet?opcion=15&buildingId=" + selectValue)
                    .then(response => response.json())
                    .then(data => {
                        let roomSelect = document.getElementById("room-select2");
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
        <select name="room-select" id="room-select2">
            <option value="0">Selecciona un aula</option>
            <%
                List<Aulas> lista4 = (List<Aulas>) request.getAttribute("aulas");
                if (lista4 != null) {
                    for (Aulas a : lista4) {
            %>
            <option value="<%= a.getId_aula()%>"><%= a.getName()%></option>
            <%
                    }
                }
            %>
        </select>
        <br><br>
        <input type="hidden" name="id">
        <input type="text" name="titulo" placeholder="Titulo"><br><br>
        <input type="text" name="descripcion" placeholder="Descripcion"><br><br>
        <input type="datetime-local" name="start" placeholder="Fecha de inicio"><br><br>
        <input type="datetime-local" name="end" placeholder="Fecha de finalizacion"><br><br>


        <br><br>
        <button type="submit" >Editar Reserva</button>
        <br><br>
        <a id="cerrar-modal" class="message">Cerrar</a>
    </form>

</dialog>

</body>
<script>

    const btnAbrirModal = document.querySelectorAll(".abrir-modal");
    const btnCerrarModal = document.querySelector("#cerrar-modal");
    const modal = document.querySelector("#modal");
    const form = modal.querySelector("form");

    btnAbrirModal.forEach((btn) => {
        btn.addEventListener("click", () => {
            const reservaId = btn.getAttribute("data-reserva-id");
            const inputId = form.querySelector("input[name='id']");
            inputId.value = reservaId;

            modal.showModal();
        });
    });

    btnCerrarModal.addEventListener("click",()=>{
        modal.close();
        form.reset();
    })


</script>
</html>
