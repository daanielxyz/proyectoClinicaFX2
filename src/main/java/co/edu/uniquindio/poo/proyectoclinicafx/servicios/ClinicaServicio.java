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
    public void cancelarCita(String id) throws Exception {
        if (id == null || id.trim().isEmpty()) {
            throw new Exception("El ID de la cita es obligatorio para cancelar.");
        }
        citaServicio.cancelarCita(id);
    }

    @Override
    public List<Servicio> listarServicios() {
        return clinica.getServicios();
    }

    public Paciente buscarPacientePorCedula(String cedula) throws Exception {
        if (cedula == null || cedula.trim().isEmpty()) {
            throw new Exception("La cédula es obligatoria para buscar un paciente.");
        }
        Paciente paciente = pacienteServicio.buscarPorCedula(cedula);
        if (paciente == null) {
            throw new Exception("Paciente con cédula " + cedula + " no encontrado.");
        }
        return paciente;
    }

    public Servicio buscarServicioPorNombre(String nombre) throws Exception {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("El nombre del servicio es obligatorio para buscar.");
        }
        Servicio servicio = clinica.getServicios().stream()
                .filter(s -> s.getNombre().equals(nombre))
                .findFirst()
                .orElse(null);
        if (servicio == null) {
            throw new Exception("Servicio " + nombre + " no encontrado.");
        }
        return servicio;
    }
}
