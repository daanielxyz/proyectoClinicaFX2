package co.edu.uniquindio.poo.proyectoclinicafx.servicios;

import co.edu.uniquindio.poo.proyectoclinicafx.modelo.Cita;
import co.edu.uniquindio.poo.proyectoclinicafx.repositorios.CitaRepositorio;
import co.edu.uniquindio.poo.proyectoclinicafx.utils.EnvioEmail;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class CitaServicio {
    private final CitaRepositorio citaRepositorio;
    private final EnvioEmail envioEmail;

    public void agendarCita(Cita cita) throws Exception {
        citaRepositorio.guardar(cita);
        String mensaje = "Cita agendada para " + cita.getFecha() +
                ". Servicio: " + cita.getServicio().getNombre() +
                ". Total: $" + cita.getFactura().getTotal();
        EnvioEmail.enviarNotificacion(cita.getPaciente().getEmail(), "Cita Agendada", mensaje);
    }

    public List<Cita> listarCitas() {
        return citaRepositorio.obtenerTodas();
    }

    public void cancelarCita(String id) {
        citaRepositorio.eliminar(id);
    }
}
