<%--

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Agregar reserva</title>
    <link rel = "stylesheet" href = css/styles.css>
</head>
<body>
<h1>Manejar reservas</h1>
<br/>
<form action = "../reserva-agregar" method = "post" enctype = "multipart/form-data">

    <label for = "nombre"> Nombre de la reserva: </label>
    <input type = "text" id = "nombre" name = "nombre" placeholder="Reserva">
    <br/>
    <label for = "descripcion"> Descripcion: </label>
    <input type = "text" id = "descripcion" name = "descripcion" placeholder="Descripcion">
    <br/>
    <label for = "usuario"> Usuario: </label>
    <input type="text" id = "usuario" name = "usuario">
    <br/>
    <label for = "horaInicio"> Hora inicio: </label>
    <input type = "datetime-local" id = "horaInicio" name = "horaInicio" placeholder="Hora Inicio">
    <br/>
    <label for = "horaFin"> Hora fin: </label>
    <input type="datetime-local" id = "horaFin" name = "horaInicio" placeholder = "Hora inicio">
    <br/>

    <input type = "submit" value = "Agregar Reserva">
</form>
</body>
</html>