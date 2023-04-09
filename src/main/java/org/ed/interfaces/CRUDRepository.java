package org.ed.interfaces;

import org.ed.exceptions.CRUDException;

public interface CRUDRepository<T>{

    void create(T t) throws CRUDException;
    T read(Long id) throws CRUDException;
    void update(T t) throws CRUDException;
    void delete(String id) throws CRUDException;

}
