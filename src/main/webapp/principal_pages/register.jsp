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
    <title>SIRA REGISTER</title>
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
        <form method="post" action="register-servlet">
            <input type="text" name="nombre" placeholder="Nombres"><br><br>
            <input type="text" name="apellido" placeholder="Apellidos"><br><br>
            <input type="email" name="correo" placeholder="Correo"><br><br>
            <input type="text" name="matricula" placeholder="Matricula (opccional)"><br><br>
            <input type="password" name="password" placeholder="Contraseña"><br><br>
            <select id="rol-select" name="rol-select">
                <option value="0" >Selecciona un rol</option>
                <option value="1" >Administrador</option>
                <option value="2" >Docente</option>
                <option value="3" >Alumno</option>

            </select><br><br>
            <button type="submit" id="btn-cerrar-modal-agregar-usuario">Registrar usuario</button>
        </form>
    </div>

    <div class="list user" >

        <table >
            <thead class="title_table">
            <tr>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Correo</th>
                <th>Matricula</th>
                <th>ROL</th>
                <th>Editar</th>
                <th>Eliminar</th>
            </tr>
            </thead>

            <%
                List<Usuario> usuarios = (List<Usuario>)request.getAttribute("usuarios");
                if(usuarios != null){
                    for(Usuario usuario : usuarios){
            %>
            <tr>
                <td><%= usuario.getFirst_name()%></td>
                <td><%= usuario.getLast_name()%></td>
                <td><%= usuario.getCorreo()%></td>
                <td><%= usuario.getMatricula()%></td>
                <td><%= usuario.getrol_name()%></td>
                <td>
                    <a class="abrir-modal" data-reserva-id="<%= usuario.getId_usuario()%>">
                        <img src="styles/icons/edit-regular-240.png" alt="Editar reserva" width="30" height="30">
                    </a>
                </td>
                <td>
                    <a href="remove-user-form-servlet?id=<%= usuario.getId_usuario()%>">
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
    <form method="post" action="edit-user-servlet"  >
        <label class="title_form">Editar usuario</label><br><br>

        <input type="hidden" name="id" >
        <input type="text" name="f_name" placeholder="Nombre"><br><br>
        <input type="text" name="l_name" placeholder="Apellido"><br><br>
        <input type="text" name="correo" placeholder="Correo"><br><br>
        <input type="text" name="matricula" placeholder="Matricula (Opccional)"><br><br>
        <input type="text" name="password" placeholder="Contraseña"><br><br>
        <select name="id_rol" id="">
            <option value="0">Selecciona un rol</option>
            <option value="1">Administrador</option>
            <option value="2">Docente</option>
            <option value="3">Alumno</option>
        </select>



        <br><br>
        <button type="submit" >Editar Usuario</button>
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

</body>
</html>
