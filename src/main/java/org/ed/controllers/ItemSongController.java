package org.ed.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ItemSongController extends Controller {

    @FXML
    private ImageView imgLike;

    @FXML
    private ImageView imgPlay;

    @FXML
    private Label lblOptions;

    @FXML
    void activateElements(MouseEvent event) {

        imgLike.setVisible(true);
        imgPlay.setVisible(true);
        lblOptions.setVisible(true);

    }

    @FXML
    void desactiveElements(MouseEvent event) {
        imgLike.setVisible(false);
        imgPlay.setVisible(false);
        lblOptions.setVisible(false);
    }

}
