package co.edu.uniquindio.poo.proyectoclinicafx.modelo.factory;

import co.edu.uniquindio.poo.proyectoclinicafx.modelo.Factura;
import co.edu.uniquindio.poo.proyectoclinicafx.modelo.Servicio;

public class SuscripcionBasica implements Suscripcion {
    @Override
    public Factura generarFacturaCobro(Servicio servicio) {
        double precioFinal = servicio.getPrecio();
        if (servicio.getNombre().equals("Consulta General")) {
            precioFinal = 0;
        } else if (servicio.getNombre().equals("Examen de Laboratorio")) {
            precioFinal *= 0.5;
        } else if (servicio.getNombre().equals("Radiografía")) {
            precioFinal *= 0.7;
        }
        return new Factura(servicio, precioFinal);
    }

    @Override
    public boolean incluyeServicio(Servicio servicio) {
        return servicio.getNombre().equals("Consulta General") ||
                servicio.getNombre().equals("Examen de Laboratorio") ||
                servicio.getNombre().equals("Radiografía");
    }

}
