package org.ed.model;

import org.ed.exceptions.CRUDException;
import org.ed.interfaces.CRUDRepository;

public class IAlbum implements CRUDRepository<Album> {


    @Override
    public void create(Album album) throws CRUDException {

    }

    @Override
    public Album read(Long id) throws CRUDException {
        return null;
    }

    @Override
    public void update(Album album) throws CRUDException {

    }

    @Override
    public void delete(String id) throws CRUDException {

    }
}
