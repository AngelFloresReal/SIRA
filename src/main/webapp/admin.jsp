<%--
  Created by IntelliJ IDEA.
  User: kuiss
  Date: 03/07/2024
  Time: 10:19 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles/eeeee.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Monoton&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    <title>SIRA</title>
</head>
<body id="body_admin">

<img src="styles/img/asset_3_1.svg" alt="" class="asset_3_1">
<img src="styles/img/asset_3_2.svg" alt="" class="asset_3_2">
<img src="styles/img/asset_3_3.svg" alt="" class="asset_3_3">
<div class="title_sira">
    <h1>SIRA</h1>
</div>

<div class="container_admin">
    <form class="form_admin" method="post" action="login-admin">
        <input class="input_admin" type="text"  name="correo" placeholder="Correo"> <br>
        <input class="input_admin" type="password"  placeholder="Contraseña" name="password"> <br> <br>
        <input type="submit"  class="submit_admin"> <br><br>
        <a href="docent.jsp">Iniciar Sesión como docente</a> <br>
        <a href="index.jsp">Iniciar Sesion como alumno</a>
    </form> 
</div>

</body>
</html>