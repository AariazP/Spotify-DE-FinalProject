package org.ed.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.alejandroArias.model.DoubleLinkedList;
import org.ed.model.Song;
import org.ed.patterns.DataFactory;
import org.ed.patterns.MainFactory;
import org.ed.utilities.PathUtilities;

import java.io.IOException;
import java.util.Iterator;

public class SearchSongsController extends Controller{

    @FXML
    private Label artNom;

    @FXML
    private Label songNom;

    @FXML
    private VBox vBoxSongs;

    @FXML
    private VBox vBoxVideos;

    private Song ownSong;

    @FXML
    public void initialize() throws IOException {

        super.setMain(MainFactory.getMain());
        super.setData(DataFactory.getInsatance());

        inicializarDatos();

         //for (int i = 0; i < 10; i++) vBoxSongs.getChildren().add(getMain().loadFXML(PathUtilities.ITEMSONG).load());
        //for (int i = 0; i < 10; i++) vBoxVideos.getChildren().add(getMain().loadFXML(PathUtilities.ITEMVIDEO).load());

    }

    private void inicializarDatos() throws IOException {

        DoubleLinkedList<Song> list = getData().getSongs();

        if (!list.isEmpty()) {
            loadArtistsSongs(list);
        }
    }

    public void loadArtistsSongs(DoubleLinkedList<Song> songs) throws IOException{

        Iterator<Song> it = songs.iterator();
        boolean flag = false;

        while(it.hasNext()){

            getData().setSelectedSong(it.next());
            if(flag){
                vBoxSongs.getChildren().add(getMain().loadFXML(PathUtilities.ITEMSONG).load());
            }else{

                ownSong = getData().getSelectedSong();
                artNom.setText(ownSong.getAutor().getName());
                songNom.setText(ownSong.getName());
                flag = true;
            }

        }
    }

    @FXML
    void listenSong(MouseEvent event) {

        getData().setSelectedSong(ownSong);
        getMain().loadStage(PathUtilities.VIDEOVIEW);
    }

}
