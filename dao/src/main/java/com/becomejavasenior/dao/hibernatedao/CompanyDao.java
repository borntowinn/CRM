package com.becomejavasenior.dao.hibernatedao;

import com.becomejavasenior.Company;

import java.util.List;

public interface CompanyDao {
    Company getByPK(Integer id);
    List getAll();
    Company selectCompanyByContactId(int contactId);
}
