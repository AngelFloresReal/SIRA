package com.utez.edu.sira.management;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {
    public static void sendEmail(String toEmail, String title, String description, String start, String end, int building, int room) {
        // Configuración del servidor de correo electrónico
        String fromEmail = "1681897@gmail.com";
        String password = "kmns myic plkt cdka";
        String host = "smtp.gmail.com";

        // Crear una sesión de correo electrónico
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587"); // o 465 para SSL
        props.put("mail.smtp.starttls.enable", "true"); // Agregamos esta propiedad


        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            // Crear un mensaje de correo electrónico
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("Reserva completada: " + title);

            // Crear el cuerpo del mensaje
            String body = "Estimado usuario,<br><br>";
            body += "Su reserva ha sido completada con éxito.<br>";
            body += "Detalles de la reserva:<br>";
            body += "Título: " + title + "<br>";
            body += "Descripción: " + description + "<br>";
            body += "Fecha de inicio: " + start + "<br>";
            body += "Fecha de fin: " + end + "<br>";
            body += "Edificio: " + building + "<br>";
            body += "Sala: " + room + "<br>";
            body += "<br>Atentamente, SIRA";

            message.setContent(body, "text/html; charset=UTF-8");

            // Enviar el mensaje
            Transport.send(message);
            System.out.println("Correo electrónico enviado con éxito");
        } catch (MessagingException e) {
            System.out.println("Error al enviar correo electrónico: " + e.getMessage());
        }
    }
}