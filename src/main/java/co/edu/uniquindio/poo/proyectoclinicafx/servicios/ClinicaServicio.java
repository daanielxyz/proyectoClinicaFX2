package co.edu.uniquindio.poo.proyectoclinicafx.servicios;

import co.edu.uniquindio.poo.proyectoclinicafx.modelo.Cita;
import co.edu.uniquindio.poo.proyectoclinicafx.modelo.Clinica;
import co.edu.uniquindio.poo.proyectoclinicafx.modelo.Paciente;
import co.edu.uniquindio.poo.proyectoclinicafx.modelo.Servicio;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ClinicaServicio implements IClinicaServicio {
    private final PacienteServicio pacienteServicio;
    private final CitaServicio citaServicio;
    private final Clinica clinica;

    @Override
    public void registrarPaciente(Paciente paciente) throws Exception {
        pacienteServicio.registrarPaciente(paciente);
    }

    @Override
    public List<Paciente> listarPacientes() {
        return pacienteServicio.listarPacientes();
    }

    @Override
    public void agendarCita(Cita cita) throws Exception {
        citaServicio.agendarCita(cita);
    }

    @Override
    public List<Cita> listarCitas() {
        return citaServicio.listarCitas();
    }

    @Override
    public void cancelarCita(String id) {
        citaServicio.cancelarCita(id);
    }

    @Override
    public List<Servicio> listarServicios() {
        return clinica.getServicios();
    }

    public Paciente buscarPacientePorCedula(String cedula) {
        return pacienteServicio.buscarPorCedula(cedula);
    }

    public Servicio buscarServicioPorNombre(String nombre) {
        return clinica.getServicios().stream()
                .filter(s -> s.getNombre().equals(nombre))
                .findFirst()
                .orElse(null);
    }
}
