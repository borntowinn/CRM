package com.becomejavasenior.spring.interfaces;

public interface AbstractService<T> {
    T objectByPK(T param);
//    void executeInsert(int tagId, int dealId);
    T createEntity(T object);
}
