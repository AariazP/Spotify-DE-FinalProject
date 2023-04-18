package org.ed.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.ed.utilities.PathUtilities;

import java.io.IOException;

public class SearchSongsController extends Controller{




    @FXML
    private VBox vBoxSongs;

    @FXML
    private VBox vBoxVideos;


    public void initializable() {

        for (int i = 0; i < 10; i++) {
            try {
                vBoxSongs.getChildren().add(super.getMain().loadFXML(PathUtilities.ITEMSONG).load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        for (int i = 0; i < 10; i++) {
            try {
                FXMLLoader loader = super.getMain().loadFXML(PathUtilities.ITEMVIDEO);
                AnchorPane pane = loader.load();
                ItemVideoController videoController =  loader.getController();
                videoController.setMain(super.getMain());
                vBoxVideos.getChildren().add(pane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }


}
