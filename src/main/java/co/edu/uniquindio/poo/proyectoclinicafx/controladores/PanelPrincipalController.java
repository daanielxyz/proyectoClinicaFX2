package co.edu.uniquindio.poo.proyectoclinicafx.controladores;

import co.edu.uniquindio.poo.proyectoclinicafx.modelo.Cita;
import co.edu.uniquindio.poo.proyectoclinicafx.modelo.Paciente;
import co.edu.uniquindio.poo.proyectoclinicafx.modelo.Servicio;
import co.edu.uniquindio.poo.proyectoclinicafx.modelo.ServicioSuscripcionInfo;
import co.edu.uniquindio.poo.proyectoclinicafx.modelo.factory.Suscripcion;
import co.edu.uniquindio.poo.proyectoclinicafx.modelo.factory.SuscripcionFactory;
import co.edu.uniquindio.poo.proyectoclinicafx.utils.Alerta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

public class PanelPrincipalController {
    @FXML private TextField cedulaField;
    @FXML private TextField nombreField;
    @FXML private TextField telefonoField;
    @FXML private TextField emailField;
    @FXML private ComboBox<String> suscripcionCombo;
    @FXML private Button registrarBtn;
    @FXML private Label mensajeLabel;

    @FXML private Button listarPacientesBtn;
    @FXML private TextArea pacientesTextArea;

    @FXML private DatePicker fechaPicker;
    @FXML private ComboBox<String> horaCombo;
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

    @FXML private TableView<ServicioSuscripcionInfo> serviciosTable;
    @FXML private TableColumn<ServicioSuscripcionInfo, String> nombreColumn;
    @FXML private TableColumn<ServicioSuscripcionInfo, String> precioColumn;
    @FXML private TableColumn<ServicioSuscripcionInfo, String> incluidoBasicaColumn;
    @FXML private TableColumn<ServicioSuscripcionInfo, String> descuentoBasicaColumn;
    @FXML private TableColumn<ServicioSuscripcionInfo, String> precioFinalBasicaColumn;
    @FXML private TableColumn<ServicioSuscripcionInfo, String> incluidoPremiumColumn;
    @FXML private TableColumn<ServicioSuscripcionInfo, String> descuentoPremiumColumn;
    @FXML private TableColumn<ServicioSuscripcionInfo, String> precioFinalPremiumColumn;

    private ControladorPrincipal controladorPrincipal;

    @FXML
    public void initialize() {
        fechaPicker.setEditable(false);
        horaCombo.setItems(FXCollections.observableArrayList(
                "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
                "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30",
                "16:00", "16:30", "17:00"
        ));

        if (serviciosTable != null) {
            nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            precioColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));
            incluidoBasicaColumn.setCellValueFactory(new PropertyValueFactory<>("incluidoBasica"));
            descuentoBasicaColumn.setCellValueFactory(new PropertyValueFactory<>("descuentoBasica"));
            precioFinalBasicaColumn.setCellValueFactory(new PropertyValueFactory<>("precioFinalBasica"));
            incluidoPremiumColumn.setCellValueFactory(new PropertyValueFactory<>("incluidoPremium"));
            descuentoPremiumColumn.setCellValueFactory(new PropertyValueFactory<>("descuentoPremium"));
            precioFinalPremiumColumn.setCellValueFactory(new PropertyValueFactory<>("precioFinalPremium"));
        }
    }

    public void setControladorPrincipal(ControladorPrincipal controladorPrincipal) {
        this.controladorPrincipal = controladorPrincipal;
        suscripcionCombo.getItems().addAll("Basica", "Premium");

        List<Paciente> pacientes = controladorPrincipal.listarPacientes();
        pacienteCombo.getItems().addAll(pacientes != null ?
                pacientes.stream().map(Paciente::getCedula).toList() :
                Collections.emptyList());

        List<Servicio> servicios = controladorPrincipal.listarServicios();
        servicioCombo.getItems().addAll(servicios != null ?
                servicios.stream().map(Servicio::getNombre).toList() :
                Collections.emptyList());
    }

    @FXML
    public void registrarPaciente() {
        try {
            if (suscripcionCombo.getValue() == null) {
                throw new Exception("Debe seleccionar un tipo de suscripción.");
            }

            Suscripcion suscripcion = SuscripcionFactory.crearSuscripcion(suscripcionCombo.getValue());
            Paciente paciente = Paciente.builder()
                    .cedula(cedulaField.getText())
                    .nombre(nombreField.getText())
                    .telefono(telefonoField.getText())
                    .email(emailField.getText())
                    .suscripcion(suscripcion)
                    .build();
            controladorPrincipal.registrarPaciente(paciente);

            Alerta.mostrarExito("Paciente registrado con éxito.");

            pacienteCombo.getItems().clear();
            List<Paciente> pacientes = controladorPrincipal.listarPacientes();
            pacienteCombo.getItems().addAll(pacientes != null ?
                    pacientes.stream().map(Paciente::getCedula).toList() :
                    Collections.emptyList());

            cedulaField.clear();
            nombreField.clear();
            telefonoField.clear();
            emailField.clear();
            suscripcionCombo.getSelectionModel().clearSelection();
        } catch (Exception e) {
            Alerta.mostrarError(e.getMessage());
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
            if (horaCombo.getValue() == null) {
                throw new Exception("Debe seleccionar una hora.");
            }

            LocalTime hora = Cita.parsearHora(horaCombo.getValue());
            if (fechaPicker.getValue() == null) {
                throw new Exception("La fecha es obligatoria.");
            }

            LocalDate fechaSeleccionada = fechaPicker.getValue();
            LocalDateTime fecha = LocalDateTime.of(fechaSeleccionada, hora);

            Paciente paciente = controladorPrincipal.buscarPacientePorCedula(pacienteCombo.getValue());
            Servicio servicio = controladorPrincipal.buscarServicioPorNombre(servicioCombo.getValue());

            Cita cita = new Cita(fecha, paciente, servicio);
            controladorPrincipal.agendarCita(cita);

            Alerta.mostrarExito("Cita agendada con éxito. ID: " + cita.getId());

            fechaPicker.setValue(null);
            horaCombo.getSelectionModel().clearSelection();
            pacienteCombo.getSelectionModel().clearSelection();
            servicioCombo.getSelectionModel().clearSelection();
        } catch (Exception e) {
            Alerta.mostrarError("Error al agendar la cita: " + e.getMessage());
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
            if (idCitaCancelarField.getText() == null || idCitaCancelarField.getText().trim().isEmpty()) {
                throw new Exception("El ID de la cita es obligatorio para cancelar.");
            }

            controladorPrincipal.cancelarCita(idCitaCancelarField.getText());

            Alerta.mostrarExito("Cita cancelada con éxito.");

            idCitaCancelarField.clear();
        } catch (Exception e) {
            Alerta.mostrarError(e.getMessage());
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


        Suscripcion suscripcionBasica = SuscripcionFactory.crearSuscripcion("Basica");
        Suscripcion suscripcionPremium = SuscripcionFactory.crearSuscripcion("Premium");

        ObservableList<ServicioSuscripcionInfo> tableData = FXCollections.observableArrayList();
        for (Servicio s : controladorPrincipal.listarServicios()) {

            boolean incluidoBasica = suscripcionBasica.incluyeServicio(s);
            boolean incluidoPremium = suscripcionPremium.incluyeServicio(s);


            double precioOriginal = s.getPrecio();
            double precioFinalBasica = suscripcionBasica.generarFacturaCobro(s).getTotal();
            double precioFinalPremium = suscripcionPremium.generarFacturaCobro(s).getTotal();


            double descuentoBasica = precioOriginal > 0 ? (precioOriginal - precioFinalBasica) / precioOriginal : 0.0;
            double descuentoPremium = precioOriginal > 0 ? (precioOriginal - precioFinalPremium) / precioOriginal : 0.0;

            tableData.add(new ServicioSuscripcionInfo(
                    s.getNombre(),
                    precioOriginal,
                    incluidoBasica,
                    descuentoBasica,
                    precioFinalBasica,
                    incluidoPremium,
                    descuentoPremium,
                    precioFinalPremium
            ));
        }

        serviciosTable.setItems(tableData);
    }
}