package co.edu.uniquindio.poo.proyectoclinicafx.controladores;

import co.edu.uniquindio.poo.proyectoclinicafx.modelo.Clinica;
import co.edu.uniquindio.poo.proyectoclinicafx.modelo.Cita;
import co.edu.uniquindio.poo.proyectoclinicafx.modelo.Paciente;
import co.edu.uniquindio.poo.proyectoclinicafx.modelo.Servicio;
import co.edu.uniquindio.poo.proyectoclinicafx.repositorios.CitaRepositorio;
import co.edu.uniquindio.poo.proyectoclinicafx.repositorios.PacienteRepositorio;
import co.edu.uniquindio.poo.proyectoclinicafx.servicios.ClinicaServicio;
import co.edu.uniquindio.poo.proyectoclinicafx.servicios.PacienteServicio;
import co.edu.uniquindio.poo.proyectoclinicafx.servicios.CitaServicio;
import co.edu.uniquindio.poo.proyectoclinicafx.utils.EnvioEmail;
import lombok.Getter;

import java.util.List;

public class ControladorPrincipal {
    private static ControladorPrincipal instance;
    @Getter
    private final Clinica clinica;
    private final ClinicaServicio clinicaServicio;

    private ControladorPrincipal() {
        this.clinica = new Clinica();
        PacienteRepositorio pacienteRepositorio = new PacienteRepositorio();
        CitaRepositorio citaRepositorio = new CitaRepositorio();
        EnvioEmail envioEmail = new EnvioEmail();

        PacienteServicio pacienteServicio = new PacienteServicio(pacienteRepositorio);
        CitaServicio citaServicio = new CitaServicio(citaRepositorio, envioEmail);
        this.clinicaServicio = new ClinicaServicio(pacienteServicio, citaServicio, clinica);
    }

    public static ControladorPrincipal getInstance() {
        if (instance == null) {
            instance = new ControladorPrincipal();
        }
        return instance;
    }

    public void registrarPaciente(Paciente paciente) throws Exception {
        clinicaServicio.registrarPaciente(paciente);
    }

    public List<Paciente> listarPacientes() {
        return clinicaServicio.listarPacientes();
    }

    public void agendarCita(Cita cita) throws Exception {
        clinicaServicio.agendarCita(cita);
    }

    public List<Cita> listarCitas() {
        return clinicaServicio.listarCitas();
    }

    public void cancelarCita(String id) {
        clinicaServicio.cancelarCita(id);
    }

    public List<Servicio> listarServicios() {
        return clinicaServicio.listarServicios();
    }

    public Paciente buscarPacientePorCedula(String cedula) {
        return clinicaServicio.buscarPacientePorCedula(cedula);
    }

    public Servicio buscarServicioPorNombre(String nombre) {
        return clinicaServicio.buscarServicioPorNombre(nombre);
    }
}
