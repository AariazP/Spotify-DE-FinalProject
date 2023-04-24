package org.ed.controllers;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Slider;
import javafx.scene.media.MediaPlayer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SliderUpdater implements Runnable {

    private Slider slider;
    private MediaPlayer mediaPlayer;

    public SliderUpdater(Slider slider, MediaPlayer mediaPlayer) {
        this.slider = slider;
        this.mediaPlayer = mediaPlayer;
    }


    @Override
    public void run() {

        try {
            while (true) {
                Platform.runLater(() -> slider.setValue(mediaPlayer.getCurrentTime().toSeconds()));
                slider.setShowTickMarks(true);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }


}