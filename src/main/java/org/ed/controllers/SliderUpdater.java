package org.ed.controllers;

import javafx.concurrent.Task;
import javafx.scene.control.Slider;
import javafx.scene.media.MediaPlayer;

public class SliderUpdater extends Task<Void> {
    private Slider slider;
    private MediaPlayer mediaPlayer;

    public SliderUpdater(Slider slider, MediaPlayer mediaPlayer) {
        this.slider = slider;
        this.mediaPlayer = mediaPlayer;
    }

    @Override
    protected Void call() throws Exception {
        while (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            updateProgress(mediaPlayer.getCurrentTime().toMillis(), mediaPlayer.getTotalDuration().toMillis());
            Thread.sleep(1000); // espera 1 segundo antes de actualizar de nuevo
        }
        return null;
    }

    @Override
    protected void updateProgress(double current, double total) {
        super.updateProgress(current, total);
        slider.setMax(total);
        slider.setValue(current);
    }
}