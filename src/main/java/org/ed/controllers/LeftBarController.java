package org.ed.controllers;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.ed.patterns.DataFactory;
import org.ed.patterns.MainFactory;
import org.ed.utilities.MethodsUtilities;
import org.ed.utilities.PathUtilities;
import org.ed.utilities.ViewUtilities;

import java.io.IOException;

public class LeftBarController extends Controller {


    @FXML
    private ImageView imgLike;

    @FXML
    private ImageView imgSong;

    @FXML
    private Label lblArtist;

    @FXML
    private Label lblDuracion;

    @FXML
    private Label lblInstant;

    @FXML
    private ImageView lblLetra;

    @FXML
    private Label lblNameSong;

    @FXML
    private ImageView lblSonido;

    @FXML
    private ImageView lblStackReproduciendo;

    @FXML
    private Slider sliderVolume;
    @FXML
    private Slider sliderSong;
    @FXML
    private ScrollPane paneCenter;
    private ScrollPane paneHome;
    private ScrollPane paneSearch;
    private ScrollPane paneLibrary;
    @FXML
    private ImageView imgPlay;

    private MediaPlayer mediaPlayer;

    @FXML
    private VBox VBoxPlaylist;


    @FXML
    void createPlaylist(MouseEvent event) {

    }

    @FXML
    void loadEpisodes(MouseEvent event) {

    }

    @FXML
    void loadHome(MouseEvent event) throws IOException {
        if (paneHome == null) { // Si no se ha cargado
            loadHomeFXML();
        } else { // Si ya se cargo
            paneCenter.setContent(paneHome);
           // paneCenter.getChildren().add(paneHome);
        }
    }

    @FXML
    void loadLibrary(MouseEvent event) throws IOException {
        // Si no se ha cargado
        loadLibraryFXML();

    }

    private void loadLibraryFXML() throws IOException {
        FXMLLoader loader = getMain().loadFXML(PathUtilities.LIBRARY);
        paneLibrary = loader.load();
        paneLibrary = getMain().loadFXML(PathUtilities.LIBRARY).load();
        LibraryController libController = loader.getController();
        libController.setLeftBarController(this);
        paneCenter.setContent(paneLibrary);
        //paneCenter.getChildren().clear();
        //paneCenter.getChildren().add(paneHome);
        libController.setLeftBarController(this);
        paneCenter.setContent(paneLibrary);
    }

    @FXML
    void loadLikesSong(MouseEvent event) {

    }

    @FXML
    void loadSearch(MouseEvent event) throws IOException {
        if (paneSearch == null) { // Si no se ha cargado
            loadSearchFXML();
        } else { // Si ya se cargo
            paneCenter.setContent(paneSearch);
            //paneCenter.getChildren().clear();
            //paneCenter.getChildren().add(paneSearch);
        }
    }

    @FXML
    void mute(MouseEvent event) {

    }

    @FXML
    void next(MouseEvent event) {

    }

    @FXML
    void play(MouseEvent event) {
        play("file:/C:/Users/anrum/Downloads/badBunny.mp3");
    }


    public void play(String path) {
        Media media = new Media(path);
        mediaPlayer = new MediaPlayer(media);
        Task<Void> sliderTask = new SliderUpdater(sliderSong, mediaPlayer);
        sliderSong.valueProperty().bind(sliderTask.progressProperty());
        new Thread(sliderTask).start();
        sliderSong.setValue(0);
        sliderSong.setMax(mediaPlayer.getTotalDuration().toSeconds()); // actualiza el max del slider
        mediaPlayer.currentTimeProperty().addListener((observableValue, duration, current) -> {
            lblInstant.setText(MethodsUtilities.convertToMinutesSeconds(current.toSeconds())); // actualiza el label con el tiempo actual de la canción
        });

        if (mediaPlayer.getStatus() != MediaPlayer.Status.PLAYING) {

            imgPlay.setImage(new Image(ViewUtilities.PAUSE));
            mediaPlayer.play();


        } else {

            imgPlay.setImage(new Image(ViewUtilities.PLAY));
            mediaPlayer.pause();

        }
        setVolume();
    }


    @FXML
    void previous(MouseEvent event) {

    }

    @FXML
    void randomSongs(MouseEvent event) {

    }

    @FXML
    void repeat(MouseEvent event) {

    }

    @FXML
    void setInstant(MouseEvent event) {
        sliderSong.setValue(sliderSong.getValue());
        sliderSong.setShowTickMarks(true);
    }

    @FXML
    void setVolume(MouseEvent event) {
        setVolume();
    }

    private void setVolume() {
        if (mediaPlayer != null) mediaPlayer.setVolume(sliderVolume.getValue() / 100);
    }

    /**
     * Carga el home desde el fxml
     */
    public void loadHomeFXML() throws IOException {

        FXMLLoader loader = getMain().loadFXML(PathUtilities.HOME);
        paneHome = loader.load();
        paneHome = getMain().loadFXML(PathUtilities.HOME).load();
        HomeController homeController = loader.getController();
        homeController.setLeftBarController(this);
        paneCenter.setContent(paneHome);
        //paneCenter.getChildren().clear();
        //paneCenter.getChildren().add(paneHome);
        homeController.setLeftBarController(this);
        paneCenter.setContent(paneHome);
        //paneCenter.getChildren().clear();
        //paneCenter.getChildren().add(paneHome);

    }

    private void loadSearchFXML() throws IOException {

        FXMLLoader loader = getMain().loadFXML(PathUtilities.SEARCH);
        paneSearch = loader.load();
        Controller controller = loader.getController();
        SearchController searchController = (SearchController) controller;
        searchController.setLeftBarController(this);
        paneCenter.setContent(paneSearch);
        //paneCenter.getChildren().clear();
        //paneCenter.getChildren().add(paneSearch);

    }

    public void loadPlaylist() throws IOException {

        for (int i = 0; i < 100; i++) VBoxPlaylist.getChildren().add(getMain().loadFXML(PathUtilities.PLAYLIST).load());

    }



    @FXML
    void initialize() throws IOException {
        super.setMain(MainFactory.getMain());
        super.setData(DataFactory.getInstance());
        loadHomeFXML();
        loadPlaylist();
    }
}
