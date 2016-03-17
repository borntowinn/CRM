package com.becomejavasenior.dao.hibernatedao;

public interface GeneralDao<T> {
    T create (T object);
    void update(T object);
    void delete(T object);
}
