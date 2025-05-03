package co.edu.uniquindio.poo.proyectoclinicafx.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Cita {
    private String id;
    private LocalDateTime fecha;
    private Paciente paciente;
    private Servicio servicio;
    private Factura factura;

    public Cita(LocalDateTime fecha, Paciente paciente, Servicio servicio) throws Exception {
        if (fecha == null) {
            throw new Exception("La fecha es obligatoria.");
        }
        if (paciente == null) {
            throw new Exception("El paciente es obligatorio.");
        }
        if (servicio == null) {
            throw new Exception("El servicio es obligatorio.");
        }

        this.id = UUID.randomUUID().toString();
        this.fecha = fecha;
        this.paciente = paciente;
        this.servicio = servicio;
        this.factura = paciente.getSuscripcion().generarFacturaCobro(servicio);
    }

    public static LocalTime parsearHora(String horaTexto) throws Exception {
        if (horaTexto == null || horaTexto.trim().isEmpty()) {
            throw new Exception("La hora es obligatoria.");
        }
        try {
            return LocalTime.parse(horaTexto, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (DateTimeParseException e) {
            throw new Exception("Formato de hora inválido. Use 'HH:mm' (ej. 14:30).");
        }
    }
}
