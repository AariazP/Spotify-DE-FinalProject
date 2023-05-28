package org.ed.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.ed.model.Song;
import org.ed.patterns.DataFactory;
import org.ed.patterns.MainFactory;
import org.ed.utilities.PathUtilities;

import java.io.IOException;

public class ItemSongController extends Controller {

    @FXML
    private ImageView imgLike;

    @FXML
    private ImageView imgLiked;

    private Song own;
    @FXML
    private Label artista;
    @FXML
    private ImageView imgPlay;
    @FXML
    private Label nombre;
    @FXML
    private Label lblOptions;

    @FXML
    public void initialize() throws IOException {

        super.setMain(MainFactory.getMain());
        super.setData(DataFactory.getInstance());

        own = getData().getSelectedSong();
        nombre.setText(own.getName());
        artista.setText(own.getAutor().getName());
    }

    @FXML
    void activateElements(MouseEvent event) {

        if(getData().getFavSongs().contains(own)){

            imgLiked.setVisible(true);
        }else {
            imgLike.setVisible(true);
        }

        imgPlay.setVisible(true);
        lblOptions.setVisible(true);
    }

    @FXML
    void desactiveElements(MouseEvent event) {

        imgLike.setVisible(false);
        imgLiked.setVisible(false);
        imgPlay.setVisible(false);
        lblOptions.setVisible(false);
    }

    @FXML
    void listenSong(MouseEvent event) {

        getData().setSelectedSong(own);
        getMain().loadStage(PathUtilities.VIDEOVIEW);
    }

    @FXML
    void likeSong(MouseEvent event) {

        if(!getData().getFavSongs().contains(own)){

            getData().setFavSongs(own);
            getData().setPilDeshacer(own);
        } else{

            getData().resetFavSongs(own);
            getData().setPilDeshacer(own);
        }
    }
}
