package org.ed.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HomeController extends Controller{


    private LeftBarController leftBarController;

    @FXML
    private ComboBox<String> cmbOptions;

    @FXML
    private GridPane gridRecommend;




    @FXML
    void initialize() {
        cmbOptions.getItems().addAll("Cuenta", "Perfil", "Sesión privada", "Preferencias", "Cerrar sesión", "Acerca de");
        cmbOptions.getSelectionModel().select(0);
        cmbOptions.setOnAction(event -> {
            loadSettings();
        });
    }

    private void loadSettings() {
        switch (cmbOptions.getSelectionModel().getSelectedIndex()) {
           /* case 0 -> loadRecommendations();
            case 1 -> loadNewReleases();
            case 2 -> loadTop50();
            case 3 -> loadTop50Country();*/
        }
    }


}
