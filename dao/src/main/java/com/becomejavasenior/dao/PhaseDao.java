package com.becomejavasenior.dao;

import com.becomejavasenior.Phase;

import java.util.List;

/**
 * Created by Default71721 on 29.01.16.
 */
public interface PhaseDao {
    Phase create(Phase object) throws RuntimeException;
    Phase persist(Phase object)  throws RuntimeException;
    Phase getByPK(Integer id) throws RuntimeException;
    void update(Phase object) throws RuntimeException;
    void delete(Integer id) throws RuntimeException;
    List<Phase> getAll() throws RuntimeException;
}
