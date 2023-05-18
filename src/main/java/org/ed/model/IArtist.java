package org.ed.model;

import lombok.Getter;
import lombok.Setter;
import org.alejandroArias.model.BinaryTree;
import org.ed.exceptions.CRUDException;
import org.ed.interfaces.CRUDRepository;
import org.ed.services.DBConnection;

@Getter
@Setter
public class IArtist implements CRUDRepository<Artist> {

    BinaryTree<Artist> artists = new BinaryTree<>();


    @Override
    public void create(Artist artist) throws CRUDException {

        if(!artists.add(artist)){

            throw new CRUDException("The artist already exists");
        }

        DBConnection.getInstance().saveArtists(artist);
    }

    @Override
    public Artist read(Long id) throws CRUDException {
        return null;
    }

    @Override
    public void update(Artist artist) throws CRUDException {

    }

    @Override
    public void delete(String id) throws CRUDException {



    }
}
