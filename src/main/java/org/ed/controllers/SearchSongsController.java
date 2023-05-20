package org.ed.controllers;

import javafx.fxml.FXML;
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
    private VBox vBoxSongs;

    @FXML
    private VBox vBoxVideos;

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
            System.out.println(list.peekFirst().getName());
            loadArtistsSongs(list);
        }
    }

    public void loadArtistsSongs(DoubleLinkedList<Song> songs) throws IOException{

        Iterator<Song> it = songs.iterator();

        while(it.hasNext()){

            getData().setSelectedSong(it.next());
            vBoxSongs.getChildren().add(getMain().loadFXML(PathUtilities.ITEMSONG).load());
        }
    }
}
