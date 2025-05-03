package co.edu.uniquindio.poo.proyectoclinicafx.modelo.factory;

import co.edu.uniquindio.poo.proyectoclinicafx.modelo.Factura;
import co.edu.uniquindio.poo.proyectoclinicafx.modelo.Servicio;

public interface Suscripcion {
    Factura generarFacturaCobro(Servicio servicio);
}

