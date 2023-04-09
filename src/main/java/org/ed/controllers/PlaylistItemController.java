package org.ed.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class PlaylistItemController extends Controller{

    private static  int id = 0;

    @FXML
    private Label lblNombrePlaylist;

    @FXML
    void setColorLabel(MouseEvent event) {
        lblNombrePlaylist.setStyle("-fx-text-fill: #ffffff;");
    }

    @FXML
    void setDefault(MouseEvent event) {
        lblNombrePlaylist.setStyle("-fx-text-fill: RGB(178, 178, 178)");
    }


    @FXML
    void initialize() {
        lblNombrePlaylist.setText("Playlist número" + id);
        id++;
    }


}
