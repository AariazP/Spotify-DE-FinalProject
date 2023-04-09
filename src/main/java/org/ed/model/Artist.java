package org.ed.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This class represents an Artist
 * @author Alejandro Arias
 * @version 1.0
 * @since 2020-11-17
 */


@Getter
@Setter
public class Artist extends User{

    // This is the attribute that represents if the artist is a band or not
    private Boolean isBand;

    //constructor
    public Artist() {
        super();
    }

    //to string


    @Override
    public String toString() {
        return "Artist{" + super.toString() +
                "isBand=" + isBand +
                '}';
    }
}
