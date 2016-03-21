package com.becomejavasenior.spring;

import com.becomejavasenior.Contact;
import com.becomejavasenior.spring.interfaces.AbstractService;
import implementations.entitiesImpl.AbstractDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ContactServImpl implements AbstractService<Contact> {
    @Autowired
    AbstractDaoImpl<Contact> dao;

    @Override
    @Transactional
    public Contact objectByPK(Contact param) {
        return dao.getByPK(param.getId());
    }

    @Override
    @Transactional
    public Contact createEntity(Contact object) {
        return dao.create(object);
    }
}
