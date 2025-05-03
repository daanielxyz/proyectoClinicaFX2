package co.edu.uniquindio.poo.proyectoclinicafx.servicios;

import co.edu.uniquindio.poo.proyectoclinicafx.modelo.Cita;
import co.edu.uniquindio.poo.proyectoclinicafx.modelo.Paciente;
import co.edu.uniquindio.poo.proyectoclinicafx.modelo.Servicio;

import java.util.List;

public interface IClinicaServicio {
    void registrarPaciente(Paciente paciente) throws Exception;
    List<Paciente> listarPacientes();
    void agendarCita(Cita cita) throws Exception;
    List<Cita> listarCitas();
    void cancelarCita(String id) throws Exception;
    List<Servicio> listarServicios();
}
