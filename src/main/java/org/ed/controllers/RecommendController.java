package org.ed.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class RecommendController extends Controller{

    private HomeController leftBarController;


    @FXML
    private ImageView imgPlay;

    @FXML
    private ImageView imgRecommend;

    @FXML
    private Label lblRecommend;

    @FXML
    private AnchorPane pane;


    @FXML
    void playRecommend(MouseEvent event) {

    }

    @FXML
    void setCursor(MouseEvent event) {
        pane.setStyle("-fx-cursor: hand;");
        imgPlay.setVisible(true);
    }


    @FXML
    void setImage(MouseEvent event) {
        pane.setStyle("-fx-cursor: default;");
        imgPlay.setVisible(false);
    }

}
