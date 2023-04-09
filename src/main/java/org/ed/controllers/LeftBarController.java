package org.ed.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.ed.utilities.PathUtilities;
import org.ed.utilities.ViewUtilities;

public class LeftBarController extends Controller{




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
    private ImageView imgPlay;

    private MediaPlayer mediaPlayer;

    private boolean isPlaying = false;

    @FXML
    void createPlaylist(MouseEvent event) {

    }

    @FXML
    void loadEpisodes(MouseEvent event) {

    }

    @FXML
    void loadHome(MouseEvent event) {

    }

    @FXML
    void loadLibrary(MouseEvent event) {

    }

    @FXML
    void loadLikesSong(MouseEvent event) {

    }

    @FXML
    void loadSearch(MouseEvent event) {

    }

    @FXML
    void mute(MouseEvent event) {

    }

    @FXML
    void next(MouseEvent event) {

    }

    @FXML
    void play(MouseEvent event) {
        play("file:///Users/alejandroarias/Downloads/Bad-Bunny-Un-Verano-Sin-Ti/Bad-Bunny-Efecto.mp3");
    }


    public void play(String path){
        Media media = new Media(path);
        mediaPlayer = new MediaPlayer(media);
        isPlaying = !isPlaying;
        if (isPlaying == true) {
            imgPlay.setImage(new Image(ViewUtilities.PAUSE));
            mediaPlayer.play();
            setVolume();
        } else {
            imgPlay.setImage(new Image(ViewUtilities.PLAY));
            mediaPlayer.pause();
            setVolume();
        }
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
        
    }

    @FXML
    void setVolume(MouseEvent event) {
        setVolume();
    }

    private void setVolume() {
        mediaPlayer.setVolume(sliderVolume.getValue() / 100);
    }

    public void initializeData(){

    }


}
