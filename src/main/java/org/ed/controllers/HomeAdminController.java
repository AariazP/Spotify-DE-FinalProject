package org.ed.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.ed.patterns.MainFactory;
import org.ed.utilities.PathUtilities;

import java.io.IOException;

public class HomeAdminController extends Controller{

    @FXML
    private VBox VBoxPlaylist;

    @FXML
    private BorderPane borderPane;

    @FXML
    private AnchorPane paneCenter;

    @FXML
    void createPlaylist(MouseEvent event) {

    }

    @FXML
    void loadHome(MouseEvent event) {

    }

    @FXML
    void loadLibrary(MouseEvent event) {

    }

    @FXML
    void loadSearch(MouseEvent event) {

    }

    @FXML
    void initialize() throws IOException {
        setMain(MainFactory.getMain());
        FXMLLoader loader = getMain().loadFXML(PathUtilities.OPTIONS_MUSIC);
        AnchorPane pane = loader.load();
        paneCenter.getChildren().clear();
        paneCenter.getChildren().add(pane);
    }
}
