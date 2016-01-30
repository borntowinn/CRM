package com.becomejavasenior.dao;

import com.becomejavasenior.Deal;

import java.util.List;

/**
 * Created by Default71721 on 28.01.16.
 */
public interface DealDao {
    Deal create(Deal object) throws RuntimeException;
    Deal persist(Deal object)  throws RuntimeException;
    Deal getByPK(Integer id) throws RuntimeException;
    void update(Deal object) throws RuntimeException;
    void delete(Integer id) throws RuntimeException;
    List<Deal> getAll() throws RuntimeException;
}
