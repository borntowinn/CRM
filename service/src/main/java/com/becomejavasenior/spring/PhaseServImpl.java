package com.becomejavasenior.spring;

import com.becomejavasenior.Phase;
import com.becomejavasenior.spring.interfaces.AbstractService;
import implementations.entitiesImpl.AbstractDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PhaseServImpl implements AbstractService<Phase> {
    @Autowired
    AbstractDaoImpl<Phase> dao;


    @Override
    @Transactional
    public Phase objectByPK(Phase param) {
        return dao.getByPK(param.getId());
    }

    @Override
    @Transactional
    public Phase createEntity(Phase object) {
        return dao.create(object);
    }
}
