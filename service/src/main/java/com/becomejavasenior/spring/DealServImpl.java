package com.becomejavasenior.spring;

import com.becomejavasenior.Deal;
import com.becomejavasenior.dao.AbstractDao;
import com.becomejavasenior.spring.interfaces.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DealServImpl implements AbstractService<Deal> {

    @Autowired
    private AbstractDao<Deal> abstractDao;

    @Override
    @Transactional
    public Deal objectByPK(Deal param) {
        return abstractDao.getByPK(param.getId());
    }

    @Override
    @Transactional
    public Deal createEntity(Deal deal) {
        return abstractDao.create(deal);
    }
}
