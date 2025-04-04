<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reserva Actualizar</title>
</head>
<body>
<h1> Actualizar reserva</h1>
<br/>
<form action = "reserva-actualizar" method = "post" enctype = "multipart/form-data">
    <input type = "hidden" name = "idReserva" value = "${reserva.idReserva}">

    <label for = "nombre"> Nombre de la reserva:</label>
    <input type = "text" id = "nombre" name = "nombre" value ="${reserva.nombre}">
    <br/>
    <label for = "descripcion">Descripción: </label>
    <input type="text" id = "descripcion" name= "descripcion" value="${reserva.descripcion}">
    <br/>
    <label for = "usuario">Usuario: </label>
    <input type="text" id = "usuario" name= "usuario" value="${reserva.usuario}">
    <br/>
    <label for = "horarioInicio">Hora inicio: </label>
    <input type="datetime-local" id = "horarioInicio" name= "horarioInicio" value="${reserva.horarioInicio}">
    <br/>
    <label for = "horarioFin">Hora inicio: </label>
    <input type="datetime-local" id = "horarioFin" name= "horarioFin" value="${reserva.horarioFin}">
    <br/>
    <input type = "submit" value ="Actualizar Reserva">
</form>
</body>
</html>
