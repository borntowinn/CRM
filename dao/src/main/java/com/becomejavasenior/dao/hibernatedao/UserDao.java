package com.becomejavasenior.dao.hibernatedao;

import com.becomejavasenior.User;

public interface UserDao<T> extends GeneralDao<T> {
    User getByEmail(String email);
}
