package co.edu.uniquindio.poo.proyectoclinicafx.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Cita {
    private String id;
    private LocalDateTime fecha;
    private Paciente paciente;
    private Servicio servicio;
    private Factura factura;

    public Cita(String id, LocalDateTime fecha, Paciente paciente, Servicio servicio) {
        this.id = id;
        this.fecha = fecha;
        this.paciente = paciente;
        this.servicio = servicio;
        this.factura = paciente.getSuscripcion().generarFacturaCobro(servicio);
    }
}
