package org.ed.controllers;

import lombok.Getter;
import lombok.Setter;
import org.alejandroArias.model.DoubleLinkedList;
import org.ed.application.Main;
import org.ed.model.Song;

@Setter
@Getter
public abstract class Controller {

    private Main main;

    private DoubleLinkedList<Song> songs;

    private Song selectSong;
}
