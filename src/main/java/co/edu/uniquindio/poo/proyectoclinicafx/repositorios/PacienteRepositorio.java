package co.edu.uniquindio.poo.proyectoclinicafx.repositorios;

import co.edu.uniquindio.poo.proyectoclinicafx.modelo.Paciente;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class PacienteRepositorio {
    private List<Paciente> pacientes = new ArrayList<>();

    public void guardar(Paciente paciente) throws Exception {
        if (paciente == null) {
            throw new IllegalArgumentException("El paciente no puede ser nulo.");
        }
        if (buscarPorCedula(paciente.getCedula()) != null) {
            throw new Exception("Paciente con cédula " + paciente.getCedula() + " ya está registrado.");
        }
        pacientes.add(paciente);
    }

    public List<Paciente> obtenerTodos() {
        return new ArrayList<>(pacientes);
    }

    public Paciente buscarPorCedula(String cedula) {
        if (cedula == null || cedula.trim().isEmpty()) {
            return null;
        }
        return pacientes.stream()
                .filter(p -> p.getCedula().equals(cedula))
                .findFirst()
                .orElse(null);
    }
}
