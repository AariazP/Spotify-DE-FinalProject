package org.ed.patterns;

import org.alejandroArias.model.CircularList;
import org.alejandroArias.model.DoubleLinkedList;
import org.alejandroArias.model.HashMap;
import org.ed.model.Domain;
import org.ed.model.Song;
import org.ed.model.User;
import org.ed.utilities.MethodsUtilities;
import org.ed.utilities.PathUtilities;

public class DataFactory {

    private static DataFactory data;
    private DoubleLinkedList<Song> songs;
    private Song selectedSong;

    private DataFactory(){

        songs = new DoubleLinkedList<>();
        selectedSong = new Song();
    }

    public static DataFactory getInstance(){

        if(data == null){

            data = new DataFactory();
        }

        return data;
    }

    public User userLogged(){

        String userName = MethodsUtilities.getUserLogged(PathUtilities.USER_FILE_LOGGED);
        HashMap<String, User> users = Domain.getInstance().getIUser().getUsers();
        return users.get(userName);
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

    public CircularList<Song> getFavSongs() {
        return userLogged().getSongs();
    }

    public void setFavSongs(Song aux){

        userLogged().getSongs().add(aux);
        Domain.getInstance().makeRelation(userLogged().getId(), aux.getId());
    }
}
