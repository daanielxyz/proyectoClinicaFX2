package co.edu.uniquindio.poo.proyectoclinicafx.modelo.factory;

public class SuscripcionFactory {
    public static Suscripcion crearSuscripcion(String tipo) {
        switch (tipo.toLowerCase()) {
            case "basica":
                return new SuscripcionBasica();
            case "premium":
                return new SuscripcionPremium();
            default:
                throw new IllegalArgumentException("Tipo de suscripción no válido.");
        }
    }
}
