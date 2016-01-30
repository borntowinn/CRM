package com.becomejavasenior.dao;

import com.becomejavasenior.dao.exception.PersistException;
import java.util.List;

/**
 * Created by Default71721 on 30.01.16.
 */
public interface AbstractDao<T> {
    T create (T object) throws PersistException;
    T persist(T object) throws PersistException;
    T getByPK(Integer id) throws PersistException;
    List<T> getAll() throws PersistException;
    void update(T object) throws PersistException;
    void delete(Integer id) throws PersistException;
}
