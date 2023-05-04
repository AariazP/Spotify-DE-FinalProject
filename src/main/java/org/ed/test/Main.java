package org.ed.test;

import org.ed.model.User;
import org.ed.patterns.UserBuilder;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {


    public static void main(String[] args) {

        File file = new File("/Users/alejandroarias/Downloads/Bad-Bunny-Un-Verano-Sin-Ti/Bad-Bunny-Efecto.mp3");
        try {
            MP3File mp3file = new MP3File(file);
            System.out.println("Duración: " + mp3file.getMP3AudioHeader().getTrackLength() + " segundos");
        } catch (IOException | TagException | ReadOnlyFileException | InvalidAudioFrameException e) {
            e.printStackTrace();
        }

    }


}
