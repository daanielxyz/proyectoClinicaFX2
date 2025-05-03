package co.edu.uniquindio.poo.proyectoclinicafx.modelo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ServicioSuscripcionInfo {
    private final StringProperty nombre;
    private final StringProperty precio;
    private final StringProperty incluidoBasica;
    private final StringProperty descuentoBasica;
    private final StringProperty precioFinalBasica;
    private final StringProperty incluidoPremium;
    private final StringProperty descuentoPremium;
    private final StringProperty precioFinalPremium;

    public ServicioSuscripcionInfo(String nombre, double precio,
                                   boolean incluidoBasica, double descuentoBasica, double precioFinalBasica,
                                   boolean incluidoPremium, double descuentoPremium, double precioFinalPremium) {
        this.nombre = new SimpleStringProperty(nombre);
        this.precio = new SimpleStringProperty(String.format("$%.1f", precio));
        this.incluidoBasica = new SimpleStringProperty(incluidoBasica ? "Sí" : "No");
        this.descuentoBasica = new SimpleStringProperty(String.format("%.0f%%", descuentoBasica * 100));
        this.precioFinalBasica = new SimpleStringProperty(String.format("$%.1f", precioFinalBasica));
        this.incluidoPremium = new SimpleStringProperty(incluidoPremium ? "Sí" : "No");
        this.descuentoPremium = new SimpleStringProperty(String.format("%.0f%%", descuentoPremium * 100));
        this.precioFinalPremium = new SimpleStringProperty(String.format("$%.1f", precioFinalPremium));
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public StringProperty precioProperty() {
        return precio;
    }

    public StringProperty incluidoBasicaProperty() {
        return incluidoBasica;
    }

    public StringProperty descuentoBasicaProperty() {
        return descuentoBasica;
    }

    public StringProperty precioFinalBasicaProperty() {
        return precioFinalBasica;
    }

    public StringProperty incluidoPremiumProperty() {
        return incluidoPremium;
    }

    public StringProperty descuentoPremiumProperty() {
        return descuentoPremium;
    }

    public StringProperty precioFinalPremiumProperty() {
        return precioFinalPremium;
    }
}
