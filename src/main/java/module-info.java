module co.edu.uniquindio.poo.proyectoClinicaFX {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.desktop;
    requires org.simplejavamail;
    requires org.simplejavamail.core;


    opens co.edu.uniquindio.poo.proyectoclinicafx.app to javafx.fxml;
    exports co.edu.uniquindio.poo.proyectoclinicafx.app;

    exports co.edu.uniquindio.poo.proyectoclinicafx.modelo;
    exports co.edu.uniquindio.poo.proyectoclinicafx.modelo.factory;

    exports co.edu.uniquindio.poo.proyectoclinicafx.controladores;
    opens co.edu.uniquindio.poo.proyectoclinicafx.controladores to javafx.fxml;

    exports co.edu.uniquindio.poo.proyectoclinicafx.servicios;
    exports co.edu.uniquindio.poo.proyectoclinicafx.repositorios;
    exports co.edu.uniquindio.poo.proyectoclinicafx.utils;
}