package org.ed.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;
import org.alejandroArias.model.CircularList;
import org.ed.model.Song;
import org.ed.patterns.DataFactory;
import org.ed.patterns.MainFactory;
import org.ed.utilities.PathUtilities;

import java.io.IOException;
import java.util.Iterator;

@Getter
@Setter
public class LibraryController extends Controller{

    private LeftBarController leftBarController;

    @FXML
    private Button btnDeshacer;

    @FXML
    private Button btnRehacer;

    @FXML
    private VBox vBoxSongs;

    @FXML
    void initialize() throws IOException{

        super.setMain(MainFactory.getMain());
        super.setData(DataFactory.getInstance());

        inicializarDatos();
    }

    private void inicializarDatos() throws IOException {

        CircularList<Song> list = getData().getFavSongs();

        if (!list.isEmpty()) {

            loadFavSongs(list);
        }
    }

    private void loadFavSongs(CircularList<Song> songs) throws IOException {

        Iterator<Song> it = songs.iterator();

        while(it.hasNext()){

            getData().setSelectedSong(it.next());
            vBoxSongs.getChildren().add(getMain().loadFXML(PathUtilities.ITEMSONG).load());
        }
    }

    @FXML
    void deshacer(MouseEvent event) throws IOException {

        Song aux = getData().getPilDeshacer().pop();
        getData().setPilRehacer(aux);

        if(getData().getFavSongs().contains(aux))getData().resetFavSongs(aux);
        else getData().setFavSongs(aux);
        loadFavSongs(getData().getFavSongs());
    }

    @FXML
    void rehacer(MouseEvent event) throws IOException {

        Song aux = getData().getPilRehacer().pop();
        getData().setPilDeshacer(aux);

        if(getData().getFavSongs().contains(aux))getData().resetFavSongs(aux);
        else getData().setFavSongs(aux);
        loadFavSongs(getData().getFavSongs());
    }
}
