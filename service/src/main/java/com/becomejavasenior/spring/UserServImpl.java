package com.becomejavasenior.spring;

import com.becomejavasenior.User;
import com.becomejavasenior.spring.interfaces.AbstractService;
import implementations.entitiesImpl.AbstractDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServImpl implements AbstractService<User> {

    @Autowired
    AbstractDaoImpl<User> dao;

    @Override
    @Transactional
    public User objectByPK(User param) {
        return dao.getByPK(param.getId());
    }

    @Override
    @Transactional
    public User createEntity(User object) {
        return dao.create(object);
    }
}
