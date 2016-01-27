package com.becomejavasenior.dao;

import com.becomejavasenior.User;

import java.util.List;

public interface UserDao {
    User create(User object) throws RuntimeException;
    User persist(User object)  throws RuntimeException;
    User getByPK(Integer id) throws RuntimeException;
    void update(User object) throws RuntimeException;
    void delete(Integer id) throws RuntimeException;
    List<User> getAll() throws RuntimeException;
}
