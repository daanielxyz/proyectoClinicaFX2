package co.edu.uniquindio.poo.proyectoclinicafx.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Factura {
    private String id;
    private LocalDateTime fecha;
    private double total;
    private double subtotal;
    private Servicio servicio;

    public Factura(Servicio servicio, double total) {
        this.id = UUID.randomUUID().toString();
        this.fecha = LocalDateTime.now();
        this.servicio = servicio;
        this.subtotal = servicio.getPrecio();
        this.total = total;
    }
}
