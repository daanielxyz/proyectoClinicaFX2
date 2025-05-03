package co.edu.uniquindio.poo.proyectoclinicafx.controladores;

import co.edu.uniquindio.poo.proyectoclinicafx.modelo.Cita;
import co.edu.uniquindio.poo.proyectoclinicafx.modelo.Paciente;
import co.edu.uniquindio.poo.proyectoclinicafx.modelo.Servicio;
import co.edu.uniquindio.poo.proyectoclinicafx.modelo.factory.Suscripcion;
import co.edu.uniquindio.poo.proyectoclinicafx.modelo.factory.SuscripcionFactory;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PanelPrincipalController {
    @FXML private TextField cedulaField;
    @FXML private TextField nombreField;
    @FXML private TextField telefonoField;
    @FXML private TextField emailField;
    @FXML
    private ComboBox<String> suscripcionCombo;
    @FXML private Button registrarBtn;
    @FXML private Label mensajeLabel;

    @FXML private Button listarPacientesBtn;
    @FXML private TextArea pacientesTextArea;

    @FXML private TextField idCitaField;
    @FXML private TextField fechaField;
    @FXML private ComboBox<String> pacienteCombo;
    @FXML private ComboBox<String> servicioCombo;
    @FXML private Button agendarBtn;
    @FXML private Label mensajeCitaLabel;

    @FXML private Button listarCitasBtn;
    @FXML private TextArea citasTextArea;

    @FXML private TextField idCitaCancelarField;
    @FXML private Button cancelarCitaBtn;
    @FXML private Label mensajeCancelarLabel;

    @FXML private Button listarServiciosBtn;
    @FXML private TextArea serviciosTextArea;

    private ControladorPrincipal controladorPrincipal;

    public void setControladorPrincipal(ControladorPrincipal controladorPrincipal) {
        this.controladorPrincipal = controladorPrincipal;
        // Inicializar ComboBox después de asignar el controlador
        suscripcionCombo.getItems().addAll("Basica", "Premium");
        pacienteCombo.getItems().addAll(controladorPrincipal.listarPacientes().stream().map(Paciente::getCedula).toList());
        servicioCombo.getItems().addAll(controladorPrincipal.listarServicios().stream().map(Servicio::getNombre).toList());
    }

    @FXML
    public void registrarPaciente() {
        try {
            Suscripcion suscripcion = SuscripcionFactory.crearSuscripcion(suscripcionCombo.getValue());
            Paciente paciente = Paciente.builder()
                    .cedula(cedulaField.getText())
                    .nombre(nombreField.getText())
                    .telefono(telefonoField.getText())
                    .email(emailField.getText())
                    .suscripcion(suscripcion)
                    .build();
            controladorPrincipal.registrarPaciente(paciente);
            mensajeLabel.setText("Paciente registrado con éxito.");
            pacienteCombo.getItems().clear();
            pacienteCombo.getItems().addAll(controladorPrincipal.listarPacientes().stream().map(Paciente::getCedula).toList());
        } catch (Exception ex) {
            mensajeLabel.setText("Error: " + ex.getMessage());
        }
    }

    @FXML
    public void listarPacientes() {
        StringBuilder sb = new StringBuilder();
        for (Paciente p : controladorPrincipal.listarPacientes()) {
            sb.append("Cédula: ").append(p.getCedula())
                    .append(", Nombre: ").append(p.getNombre())
                    .append(", Suscripción: ").append(p.getSuscripcion().getClass().getSimpleName())
                    .append("\n");
        }
        pacientesTextArea.setText(sb.toString());
    }

    @FXML
    public void agendarCita() {
        try {
            LocalDateTime fecha = LocalDateTime.parse(fechaField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            Paciente paciente = controladorPrincipal.buscarPacientePorCedula(pacienteCombo.getValue());
            if (paciente == null) throw new Exception("Paciente no encontrado");
            Servicio servicio = controladorPrincipal.buscarServicioPorNombre(servicioCombo.getValue());
            if (servicio == null) throw new Exception("Servicio no encontrado");
            Cita cita = new Cita(idCitaField.getText(), fecha, paciente, servicio);
            controladorPrincipal.agendarCita(cita);
            mensajeCitaLabel.setText("Cita agendada con éxito.");
        } catch (Exception ex) {
            mensajeCitaLabel.setText("Error: " + ex.getMessage());
        }
    }

    @FXML
    public void listarCitas() {
        StringBuilder sb = new StringBuilder();
        for (Cita c : controladorPrincipal.listarCitas()) {
            sb.append("ID: ").append(c.getId())
                    .append(", Fecha: ").append(c.getFecha())
                    .append(", Paciente: ").append(c.getPaciente().getNombre())
                    .append(", Servicio: ").append(c.getServicio().getNombre())
                    .append(", Total: $").append(c.getFactura().getTotal())
                    .append("\n");
        }
        citasTextArea.setText(sb.toString());
    }

    @FXML
    public void cancelarCita() {
        try {
            controladorPrincipal.cancelarCita(idCitaCancelarField.getText());
            mensajeCancelarLabel.setText("Cita cancelada con éxito.");
        } catch (Exception ex) {
            mensajeCancelarLabel.setText("Error: " + ex.getMessage());
        }
    }

    @FXML
    public void listarServicios() {
        StringBuilder sb = new StringBuilder();
        for (Servicio s : controladorPrincipal.listarServicios()) {
            sb.append("ID: ").append(s.getId())
                    .append(", Nombre: ").append(s.getNombre())
                    .append(", Precio: $").append(s.getPrecio())
                    .append("\n");
        }
        serviciosTextArea.setText(sb.toString());
    }
}
