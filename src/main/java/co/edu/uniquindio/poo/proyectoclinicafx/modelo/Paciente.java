package co.edu.uniquindio.poo.proyectoclinicafx.modelo;

import co.edu.uniquindio.poo.proyectoclinicafx.modelo.factory.Suscripcion;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class Paciente {
    private String telefono;
    private String nombre;
    private String cedula;
    private String email;
    private Suscripcion suscripcion;
}
