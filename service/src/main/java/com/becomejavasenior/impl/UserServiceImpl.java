package com.becomejavasenior.impl;

import com.becomejavasenior.User;
import com.becomejavasenior.abstraction.UserService;
import com.becomejavasenior.dao.UserDao;
import com.becomejavasenior.dao.jdbc.impl.UserDaoImpl;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao<User> userDao = new UserDaoImpl();

    @Override
    public User getByPK(Integer id) {
        return userDao.getByPK(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User create(User user) {
        return userDao.create(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(Integer id) {
        userDao.delete(id);
    }

    @Override
    public User getByEmail(String email) {
        User user = userDao.getByEmail(email);
        return user;
    }
}
