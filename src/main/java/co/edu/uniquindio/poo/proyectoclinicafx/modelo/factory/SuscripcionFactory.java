package co.edu.uniquindio.poo.proyectoclinicafx.modelo.factory;

public class SuscripcionFactory {

    /// PUNTO 16 DEL DOCUMENTO:
    /*
     * Asi seria para agregarlo:
     * 1. Crear una nueva clase que implemente la interfaz Suscripcion (por ejemplo, SuscripcionFamiliar).
     *    - Implementar el método generarFacturaCobro(Servicio servicio) con las reglas específicas de cobro para esta suscripción.
     *    - Implementar el método incluyeServicio(Servicio servicio) para definir qué servicios están incluidos o tienen descuentos.
     * 2. Modificar esta clase (SuscripcionFactory) para incluir la nueva suscripción en el método crearSuscripcion:
     *    - Agregar un nuevo caso en el switch o if-else para manejar el nuevo tipo (por ejemplo, "Familiar").
     *    - Retornar una nueva instancia de la clase (por ejemplo, return new SuscripcionFamiliar()).
     * 3. Actualizar la clase PanelPrincipalController para incluir la nueva suscripción en el ComboBox:
     *    - En el método setControladorPrincipal, agregar la nueva opción al ComboBox suscripcionCombo (por ejemplo, suscripcionCombo.getItems().add("Familiar")).
     * 4. (Opcional) Si la nueva suscripción afecta otras partes del sistema (como la tabla en "Listar Servicios"):
     *    - Modificar PanelPrincipalController para incluir la nueva suscripción en la tabla (por ejemplo, agregar columnas adicionales para SuscripcionFamiliar).
     * Este diseño basado en una fábrica (Factory Pattern) facilita la extensibilidad, ya que solo se necesita modificar esta clase y las referencias en el controlador,
     * sin cambiar el resto del sistema.
     */



    public static Suscripcion crearSuscripcion(String tipo) {
        return switch (tipo) {
            case "Basica" -> new SuscripcionBasica();
            case "Premium" -> new SuscripcionPremium();
            default -> throw new IllegalArgumentException("Tipo de suscripción no válido: " + tipo);
        };
    }
}
