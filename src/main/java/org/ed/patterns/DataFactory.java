package org.ed.patterns;

import org.alejandroArias.model.DoubleLinkedList;
import org.ed.model.Song;

public class DataFactory {

    private static DataFactory data;
    private DoubleLinkedList<Song> songs;
    private Song selectedSong;

    private DataFactory(){

        songs = new DoubleLinkedList<>();
        selectedSong = new Song();

    }

    public static DataFactory getInsatance(){

        if(data == null){

            data = new DataFactory();
        }

        return data;
    }


    public DoubleLinkedList<Song> getSongs() {
        return songs;
    }

    public void setSongs(DoubleLinkedList<Song> songs) {
        this.songs = songs;
    }

    public Song getSelectedSong() {
        return selectedSong;
    }

    public void setSelectedSong(Song selectedSong) {
        this.selectedSong = selectedSong;
    }
}
