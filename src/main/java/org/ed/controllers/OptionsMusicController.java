package org.ed.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import org.ed.patterns.MainFactory;
import org.ed.utilities.PathUtilities;

public class OptionsMusicController extends Controller{

    @FXML
    private ComboBox<String> cmbSettings;




    @FXML
    void initialize() {
        setMain(MainFactory.getMain());
        cmbSettings.getItems().add("Cuenta");
        cmbSettings.getItems().add("Privacidad");
        cmbSettings.getItems().add("Notificaciones");
        cmbSettings.getItems().add("Reproductor");
        cmbSettings.getItems().add("Redes Sociales");
        cmbSettings.getItems().add("Ayuda");
        cmbSettings.getItems().add("Cerrar Sesión");
        //si se selecciona una opcion se carga el fxml correspondiente
        cmbSettings.setOnAction(event -> {
            switch (cmbSettings.getValue()){
                case "Cuenta":
                    break;
                case "Privacidad":
                    break;
                case "Notificaciones":
                    break;
                case "Reproductor":
                    break;
                case "Redes Sociales":
                    break;
                case "Ayuda":
                    break;
                case "Cerrar Sesión":
                    getMain().loadStage(PathUtilities.LOGIN);
                    break;
            }
        });
    }

}
