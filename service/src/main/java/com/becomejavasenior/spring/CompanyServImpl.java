package com.becomejavasenior.spring;

import com.becomejavasenior.Company;
import com.becomejavasenior.dao.AbstractDao;
import com.becomejavasenior.spring.interfaces.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServImpl implements AbstractService<Company> {

    @Autowired
    private AbstractDao<Company> abstractDao;

    @Override
    public Company objectByPK(Company company) {
        return abstractDao.getByPK(company.getId());
    }

    @Override
    public Company createEntity(Company object) {
        return abstractDao.create(object);
    }
}
