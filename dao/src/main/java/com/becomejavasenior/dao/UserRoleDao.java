package com.becomejavasenior.dao;

import com.becomejavasenior.UserRole;

import java.util.List;

public interface UserRoleDao {
    UserRole create(UserRole object) throws RuntimeException;
    UserRole persist(UserRole object)  throws RuntimeException;
    UserRole getByPK(Integer id) throws RuntimeException;
    void update(UserRole object) throws RuntimeException;
    void delete(Integer id) throws RuntimeException;
    List<UserRole> getAll() throws RuntimeException;
}
