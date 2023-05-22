package org.ed.patterns;

import lombok.Getter;
import lombok.Setter;
import org.ed.model.Domain;
import org.ed.model.Gender;
import org.ed.model.Song;
import org.ed.model.User;
import org.ed.utilities.MethodsUtilities;

/**
 * This class is a builder for the User class.
 * @author Alejandro Arias
 * @version 1.0
 * @since 2020-10-20
 */

@Setter
@Getter
public class SongBuilder {

    // The song to be built.
    private Song song;

    public SongBuilder() {
        song = new Song();
    }

    /*
        * The following methods are the setters for the song's attributes.
     */
    public SongBuilder name(String name) {
        song.setName(name);
        return this;
    }

    public SongBuilder url(String url) {
        song.setUrl(url);
        return this;
    }

    public Song build() {
        return song;
    }

    public SongBuilder id(Long id) {
        song.setId(id);
        return this;
    }

    public SongBuilder gender(Gender gen) {
        song.setGender(gen);
        return this;
    }
}
