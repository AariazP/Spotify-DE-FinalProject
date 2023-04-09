package org.ed.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.Setter;
import org.ed.utilities.PathUtilities;

@Setter
@Getter
public class HomeController extends Controller{


    private LeftBarController leftBarController;

    @FXML
    private ComboBox<String> cmbOptions;

    @FXML
    private GridPane gridRecommend;

    @FXML
    private HBox HBoxRecentlyListen;



    void initializable() {
        cmbOptions.getItems().addAll("Cuenta", "Perfil", "Sesión privada", "Preferencias", "Cerrar sesión", "Acerca de");
        cmbOptions.getSelectionModel().select(0);
        cmbOptions.setOnAction(event -> {
            loadSettings();
        });
        loadRecommendations();
        loadRecentlyListen();
    }

    private void loadRecentlyListen() {
        try {
            for (int i = 0; i < 100; i++){
                Pane pane = getMain().loadFXML(PathUtilities.SINGER).load();
                pane.setStyle("-fx-start-margin: 30px; -fx-end-margin: 30px;");
                HBoxRecentlyListen.getChildren().add(pane);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }



    }

    private void loadRecommendations() {
        try {
            gridRecommend.getChildren().clear();
            gridRecommend.add(getMain().loadFXML(PathUtilities.RECOMMEND).load(), 0, 0);
            gridRecommend.add(getMain().loadFXML(PathUtilities.RECOMMEND).load(), 1, 0);
            gridRecommend.add(getMain().loadFXML(PathUtilities.RECOMMEND).load(), 2, 0);
            gridRecommend.add(getMain().loadFXML(PathUtilities.RECOMMEND).load(), 0, 1);
            gridRecommend.add(getMain().loadFXML(PathUtilities.RECOMMEND).load(), 1, 1);
            gridRecommend.add(getMain().loadFXML(PathUtilities.RECOMMEND).load(), 2, 1);

        }catch (Exception e) {
            e.printStackTrace();
        }

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
