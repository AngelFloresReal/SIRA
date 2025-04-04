<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="styles/eeeee.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Monoton&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    <title>SIRA</title>
</head>
<body id="body_index">



<img src="styles/img/asset_1_1.svg" alt="" class="asset_1_1">
<img src="styles/img/asset_1_2.svg" alt="" class="asset_1_2">
<img src="styles/img/asset_1_3.svg" alt="" class="asset_1_3">
<div class="title_sira">
    <h1>SIRA</h1>
</div>

<div class="container">
    <form class="form_alumno" method="POST" action="login-servlet" >
        <input type="hidden" name="token" value="${token}">
        <input class="input_alumno" type="text" id="matricula" name="matricula" placeholder="Matricula"> <br>
        <input class="input_alumno" type="password" id="password" placeholder="Contraseña" name="password"> <br> <br>
        <input type="submit" value="Iniciar sesión" class="submit_alumno"> <br><br>
        <a href="admin.jsp" >Iniciar Sesión como administrador</a> <br>
        <a href="docent.jsp">Iniciar Sesion como docente</a>
    </form>

</div>


</body>
</html>
