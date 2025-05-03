package co.edu.uniquindio.poo.proyectoclinicafx.modelo.factory;

import co.edu.uniquindio.poo.proyectoclinicafx.modelo.Factura;
import co.edu.uniquindio.poo.proyectoclinicafx.modelo.Servicio;

public class SuscripcionBasica implements Suscripcion {
    @Override
    public Factura generarFacturaCobro(Servicio servicio) {
        double precioFinal = servicio.getPrecio();
        if (servicio.getNombre().equals("Consulta General")) {
            precioFinal = 0; // Gratuito
        } else if (servicio.getNombre().equals("Examen de Laboratorio")) {
            precioFinal *= 0.5; // 50% descuento
        } else if (servicio.getNombre().equals("Radiografía")) {
            precioFinal *= 0.7; // 30% descuento
        }
        return new Factura(servicio, precioFinal);
    }
}
