package com.becomejavasenior.dao;

import com.becomejavasenior.Identified;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T extends Identified<PK>, PK extends Serializable> {

    public T create() throws Exception;
    public T persist(T object)  throws Exception;
    public T getByPK(PK key) throws Exception;
    public void update(T object) throws Exception;
    public void delete(T object) throws Exception;
    public List<T> getAll() throws Exception;
}
