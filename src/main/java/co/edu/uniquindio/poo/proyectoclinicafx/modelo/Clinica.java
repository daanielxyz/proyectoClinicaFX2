package co.edu.uniquindio.poo.proyectoclinicafx.modelo;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Clinica {
    private List<Servicio> servicios = new ArrayList<>();

    public Clinica() {
        this.servicios = new ArrayList<>();
        inicializarServicios();
    }

    private void inicializarServicios() {
        servicios.add(new Servicio("S1", "Consulta General", 50));
        servicios.add(new Servicio("S2", "Examen de Laboratorio", 80));
        servicios.add(new Servicio("S3", "Radiografía", 120));
        servicios.add(new Servicio("S4", "Fisioterapia", 100));
        servicios.add(new Servicio("S5", "Cirugía Menor", 500));
    }
}
