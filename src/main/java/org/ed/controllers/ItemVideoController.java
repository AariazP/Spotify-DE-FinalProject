package org.ed.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import org.ed.patterns.MainFactory;

public class ItemVideoController extends Controller{


    @FXML
    private ImageView imgLike;

    @FXML
    private ImageView imgYoutube;

    @FXML
    private Label lblOptions;

    @FXML
    private Rectangle rectangleImage;

    @FXML
    void activateElemente(MouseEvent event) {

        imgLike.setVisible(true);
        imgYoutube.setVisible(true);
        lblOptions.setVisible(true);
        rectangleImage.setVisible(true);
    }

    @FXML
    void desactiveElements(MouseEvent event) {
        imgLike.setVisible(false);
        imgYoutube.setVisible(false);
        rectangleImage.setVisible(false);
    }

    @FXML
    void loadVideo(MouseEvent event) {
        getMain().loadVideo();
    }

    @FXML
    void initialize(){
        super.setMain(MainFactory.getMain());
        imgLike.setVisible(false);
        imgYoutube.setVisible(false);
        rectangleImage.setVisible(false);
    }



}
