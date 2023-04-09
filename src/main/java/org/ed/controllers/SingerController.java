package org.ed.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class SingerController extends Controller {

    @FXML
    private ImageView imgPlay;

    @FXML
    private Rectangle rectangle1;

    @FXML
    private Rectangle rectangle2;

    @FXML
    private AnchorPane pane;

    @FXML
    void entered(MouseEvent event) {
        imgPlay.setVisible(true);
        rectangle1.setStyle("-fx-fill: #464646;");
        rectangle2.setStyle("-fx-stroke: #464646;");
        setStyle();
        pane.setStyle("-fx-cursor: hand;");
    }

    @FXML
    void exited(MouseEvent event) {
        imgPlay.setVisible(false);
        rectangle1.setStyle("-fx-fill: #242424;");
        rectangle2.setStyle("-fx-stroke: #242424;");
        setStyle();
    }

    private void setStyle(){
        pane.setStyle("-fx-background-color: TRANSPARENT;");
        rectangle1.setStyle("-fx-arc-height: 30;");
        rectangle1.setStyle("-fx-arc-width: 30;");
        rectangle2.setStyle("-fx-arc-height: 90;");
        rectangle2.setStyle("-fx-arc-width: 90;");
    }

}