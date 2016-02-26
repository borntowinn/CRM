package com.becomejavasenior.dao;

import com.becomejavasenior.User;
import com.becomejavasenior.dao.exception.PersistException;

public interface UserDao<T> extends AbstractDao<T>{
    User getByEmail(String email) throws PersistException;
}
