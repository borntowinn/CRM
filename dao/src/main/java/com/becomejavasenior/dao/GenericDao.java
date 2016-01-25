package com.becomejavasenior.dao;

import java.util.List;

public interface GenericDao<T> {

    public T create(T object) throws Exception;
    public T persist(T object)  throws Exception;
    public T getByPK(Integer id) throws Exception;
    public void update(T object) throws Exception;
    public void delete(Integer id) throws Exception;
    public List<T> getAll() throws Exception;
}
