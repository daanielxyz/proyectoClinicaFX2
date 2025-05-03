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
        for (Cita c : citas) {
            if (c.getFecha().equals(cita.getFecha())) {
                throw new Exception("Cita se cruza con otra en el mismo horario.");
            }
        }
        citas.add(cita);
    }

    public List<Cita> obtenerTodas() {
        return new ArrayList<>(citas);
    }

    public void eliminar(String id) {
        citas.removeIf(cita -> cita.getId().equals(id));
    }
}
