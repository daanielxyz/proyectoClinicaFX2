package co.edu.uniquindio.poo.proyectoclinicafx.modelo.factory;

public class SuscripcionFactory {

    public static Suscripcion crearSuscripcion(String tipo) {
        return switch (tipo) {
            case "Basica" -> new SuscripcionBasica();
            case "Premium" -> new SuscripcionPremium();
            default -> throw new IllegalArgumentException("Tipo de suscripción no válido: " + tipo);
        };
    }
}
