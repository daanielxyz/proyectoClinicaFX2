package co.edu.uniquindio.poo.proyectoclinicafx.modelo;

import co.edu.uniquindio.poo.proyectoclinicafx.modelo.factory.Suscripcion;
import lombok.Builder;
import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
public class Paciente {
    private String cedula;
    private String nombre;
    private String telefono;
    private String email;
    private Suscripcion suscripcion;

    @Builder
    private Paciente(String cedula, String nombre, String telefono, String email, Suscripcion suscripcion) throws Exception {
        validar(cedula, nombre, telefono, email, suscripcion);
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.suscripcion = suscripcion;
    }

    private void validar(String cedula, String nombre, String telefono, String email, Suscripcion suscripcion) throws Exception {
        if (cedula == null || cedula.trim().isEmpty()) {
            throw new Exception("La cédula es obligatoria.");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("El nombre es obligatorio.");
        }
        if (suscripcion == null) {
            throw new Exception("La suscripción es obligatoria.");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new Exception("El email es obligatorio.");
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new Exception("El email no tiene un formato válido (ejemplo: usuario@dominio.com).");
        }
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new Exception("El teléfono es obligatorio.");
        }
        if (!telefono.matches("^[0-9]{7,15}$")) {
            throw new Exception("El teléfono debe contener solo números y tener entre 7 y 15 dígitos.");
        }
    }
}
