package org.ed.model;

import lombok.Getter;
import lombok.Setter;
import org.alejandroArias.model.LinkedList;
import org.ed.exceptions.CRUDException;
import org.ed.interfaces.CRUDRepository;

@Getter
@Setter
public class ISong implements CRUDRepository<Song> {

    private LinkedList<Song> songs;

    public ISong() {
        songs = new LinkedList<>();
    }


    @Override
    public void create(Song song) throws CRUDException {

    }

    @Override
    public Song read(Long id) throws CRUDException {

        for(int i=0; i<songs.size(); i++){
            if(songs.get(i).compareId(id)) return songs.get(i);
        }

        throw new CRUDException("La canción con el id "+ id+ "no se ha encontrado");
    }

    @Override
    public void update(Song song) throws CRUDException {

    }

    @Override
    public void delete(String id) throws CRUDException {

    }
}
