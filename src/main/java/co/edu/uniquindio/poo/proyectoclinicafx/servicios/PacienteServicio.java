package co.edu.uniquindio.poo.proyectoclinicafx.servicios;


import co.edu.uniquindio.poo.proyectoclinicafx.modelo.Paciente;
import co.edu.uniquindio.poo.proyectoclinicafx.repositorios.PacienteRepositorio;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PacienteServicio {
    private final PacienteRepositorio pacienteRepositorio;

    public void registrarPaciente(Paciente paciente) throws Exception {
        pacienteRepositorio.guardar(paciente);
    }

    public List<Paciente> listarPacientes() {
        return pacienteRepositorio.obtenerTodos();
    }

    public Paciente buscarPorCedula(String cedula) {
        return pacienteRepositorio.buscarPorCedula(cedula);
    }
}
