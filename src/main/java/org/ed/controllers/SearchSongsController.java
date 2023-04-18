package org.ed.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import org.ed.patterns.MainFactory;
import org.ed.utilities.PathUtilities;

import java.io.IOException;

public class SearchSongsController extends Controller{




    @FXML
    private VBox vBoxSongs;

    @FXML
    private VBox vBoxVideos;

    @FXML
    public void initialize() throws IOException {

        super.setMain(MainFactory.getMain());
        for (int i = 0; i < 10; i++) vBoxSongs.getChildren().add(getMain().loadFXML(PathUtilities.ITEMSONG).load());
        for (int i = 0; i < 10; i++) vBoxVideos.getChildren().add(getMain().loadFXML(PathUtilities.ITEMVIDEO).load());

    }


}
