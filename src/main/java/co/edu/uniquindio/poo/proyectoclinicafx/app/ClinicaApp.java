package co.edu.uniquindio.poo.proyectoclinicafx.app;

import co.edu.uniquindio.poo.proyectoclinicafx.controladores.ControladorPrincipal;
import co.edu.uniquindio.poo.proyectoclinicafx.controladores.PanelPrincipalController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ClinicaApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        ControladorPrincipal controladorPrincipal = ControladorPrincipal.getInstance();


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/PanelPrincipal.fxml"));
        Scene scene = new Scene(loader.load(), 800, 600);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());


        PanelPrincipalController panelController = loader.getController();
        panelController.setControladorPrincipal(controladorPrincipal);

        primaryStage.setTitle("Sistema de Gestión de Clínica");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}