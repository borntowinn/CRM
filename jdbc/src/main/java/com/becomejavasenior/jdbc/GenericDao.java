package com.becomejavasenior.jdbc;

import com.becomejavasenior.Identified;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T extends Identified<PK>, PK extends Serializable> {

    public T create() throws PersistException;
    public T persist(T object)  throws PersistException;
    public T getByPK(PK key) throws PersistException;
    public void update(T object) throws PersistException;
    public void delete(T object) throws PersistException;
    public List<T> getAll() throws PersistException;
}
