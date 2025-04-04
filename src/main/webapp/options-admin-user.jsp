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

    <title>GESTOR DE USUARIOS</title>
</head>
<body>
<div class="barra">

    <img src="styles/icons/personaje_1.2.png" alt="" class="logo">
    <h1>SIRA GESTOR DE USUARIOS</h1>

    <div class="close">
        <a href="user-servlet">Volver</a>
    </div>
    
</div>

<div class="formulario-registro form_4 form_2">

    <form method="post" action="remove-user-form-servlet"  >
    <label class="title_form" >Eliminar usuario</label><br><br>
    <input type="number" name="id" placeholder="ID de usuario"><br><br>
    <button type="submit" >Eliminar Usuario</button>
    </form>

</div>

<div class="formulario-registro form_3">

    <form method="post" action="edit-user-servlet"  >
        <label class="title_form">Editar usuario</label><br><br>

        <input type="number" name="id" placeholder="ID del usuario a modificar"><br><br>
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
    </form>

</div>
</body>
</html>
