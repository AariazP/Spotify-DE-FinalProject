package org.ed.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.alejandroArias.model.DoubleLinkedList;

/**
 * This class represents an Artist
 * @author Alejandro Arias
 * @version 1.0
 * @since 2020-11-17
 */


@Getter
@Setter
public class Artist extends User implements Comparable<Artist> {

    // This is the attribute that represents if the artist is a band or not
    private Boolean isBand;
    private DoubleLinkedList<Song> ownSongs;


    //constructor
    public Artist(String name) {
        super();
        super.setName(name);
        ownSongs = new DoubleLinkedList<>();
    }

    //to string


    @Override
    public String toString() {
        return "Artist{" + super.toString() +
                "isBand=" + isBand +
                '}';
    }

    @Override
    public int compareTo(Artist o) {

        return this.getName().compareTo(o.getName());
    }
}
