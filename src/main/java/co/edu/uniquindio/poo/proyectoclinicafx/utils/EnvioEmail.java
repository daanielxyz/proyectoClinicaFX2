package co.edu.uniquindio.poo.proyectoclinicafx.utils;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

public class EnvioEmail {

    private static final String FROM_EMAIL = "proyectoclinicauq@gmail.com";
    private static final String FROM_NAME = "Clínica UQ";

    public static void enviarNotificacion(String destinatario, String asunto, String mensaje) throws Exception {

        if (destinatario == null || destinatario.trim().isEmpty() || !destinatario.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new Exception("La dirección de correo del destinatario no es válida.");
        }


        Email email = EmailBuilder.startingBlank()
                .from(FROM_NAME, FROM_EMAIL)
                .to(destinatario)
                .withSubject(asunto)
                .withPlainText(mensaje)
                .buildEmail();


        try (Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.gmail.com", 587, FROM_EMAIL, "gstw yxsp mowl zsce")
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .buildMailer()) {


            mailer.sendMail(email);
        } catch (Exception e) {
            throw new Exception("Error al enviar el correo: " + e.getMessage(), e);
        }
    }
}
