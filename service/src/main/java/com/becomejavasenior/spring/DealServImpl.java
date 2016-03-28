package com.becomejavasenior.spring;

import com.becomejavasenior.Deal;
import com.becomejavasenior.dao.AbstractDao;
import com.becomejavasenior.spring.interfaces.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DealServImpl implements AbstractService<Deal> {

    @Autowired
    private AbstractDao<Deal> abstractDao;

    @Override
    public Deal objectByPK(Deal param) {
        return abstractDao.getByPK(param.getId());
    }

    @Override
    public Deal createEntity(Deal deal) {
        return abstractDao.create(deal);
    }
}
