package co.edu.uniquindio.poo.proyectoclinicafx.repositorios;

import co.edu.uniquindio.poo.proyectoclinicafx.modelo.Cita;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CitaRepositorio {
    private List<Cita> citas = new ArrayList<>();

    public void guardar(Cita cita) throws Exception {
        if (cita == null) {
            throw new IllegalArgumentException("La cita no puede ser nula.");
        }
        for (Cita c : citas) {
            if (c.getFecha().equals(cita.getFecha())) {
                throw new Exception("Ya existe una cita programada para el mismo horario: " + cita.getFecha());
            }
        }
        citas.add(cita);
    }

    public List<Cita> obtenerTodas() {
        return new ArrayList<>(citas);
    }

    public void eliminar(String id) {
        if (id == null || id.trim().isEmpty()) {
            return;
        }
        citas.removeIf(cita -> cita.getId().equals(id));
    }
}
