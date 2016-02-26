package com.becomejavasenior.abstraction;

import com.becomejavasenior.User;

import java.util.List;

public interface UserService {
    User getByPK(Integer id);
    List<User> getAll();
    User create(User user);
    void update(User user);
    void delete(Integer id);
    User getByEmail(String email);
}
