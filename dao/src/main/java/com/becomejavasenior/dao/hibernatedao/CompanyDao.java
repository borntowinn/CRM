package com.becomejavasenior.dao.hibernatedao;

import com.becomejavasenior.Company;

public interface CompanyDao extends GeneralDao{
    Company selectCompanyByContactId(int contactId);
}
