package org.ed.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.Setter;
import org.ed.patterns.MainFactory;
import org.ed.utilities.MethodsUtilities;
import org.ed.utilities.PathUtilities;

import java.io.IOException;

@Setter
@Getter
public class HomeController extends Controller {


    private LeftBarController leftBarController;
    @FXML
    private ComboBox<String> cmbOptions;
    @FXML
    private GridPane gridRecommend;
    @FXML
    private HBox HBoxRecentlyListen;
    @FXML
    private HBox HBoxMixes;

    @FXML
    private HBox HBoxFavoritesArtist;

    @FXML
    private HBox HBoxFavoritesSongs;


    @FXML
    void initialize() throws Exception {

        super.setMain(MainFactory.getMain());
        MethodsUtilities.getOptions().forEach(option -> cmbOptions.getItems().add(option));
        cmbOptions.setOnAction(event -> loadSettings());
        loadRecommendations();
        loadRecentlyListen();

    }

    private void loadRecentlyListen() throws IOException {
        for (int i = 0; i < 20; i++) {
            Pane pane = getMain().loadFXML(PathUtilities.SINGER).load();
            pane.setStyle("-fx-start-margin: 30px; -fx-end-margin: 30px;");
            HBoxRecentlyListen.getChildren().add(pane);

            Pane pane2 = getMain().loadFXML(PathUtilities.SINGER).load();
            pane2.setStyle("-fx-start-margin: 30px; -fx-end-margin: 30px;");
            HBoxMixes.getChildren().add(pane2);

            Pane pane3 = getMain().loadFXML(PathUtilities.SINGER).load();
            pane3.setStyle("-fx-start-margin: 30px; -fx-end-margin: 30px;");
            HBoxFavoritesSongs.getChildren().add(pane3);

            Pane pane4 = getMain().loadFXML(PathUtilities.SINGER).load();
            pane4.setStyle("-fx-start-margin: 30px; -fx-end-margin: 30px;");
            HBoxFavoritesArtist.getChildren().add(pane4);
        }


    }

    private void loadRecommendations() throws Exception {

        gridRecommend.getChildren().clear();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) gridRecommend.add(getMain().loadFXML(PathUtilities.RECOMMEND).load(), i, j);
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
