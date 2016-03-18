package com.becomejavasenior.dao.hibernatedao;

import com.becomejavasenior.User;

public interface UserDao extends GeneralDao {
    User getByEmail(String email);
}
